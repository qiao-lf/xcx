package henu.xmh.service.impl;

import henu.xmh.annotation.LogAnnotation;
import henu.xmh.dao.EssayMapper;
import henu.xmh.pojo.Essay;
import henu.xmh.pojo.EssayExample;
import henu.xmh.repository.EssayRepository;
import henu.xmh.service.EssayService;
import org.apache.ibatis.session.RowBounds;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private EssayRepository essayRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Map<String, Object> findAllEssayForPage(Integer rows, Integer page) {
        EssayExample example = new EssayExample();
        List<Essay> essays = essayMapper.selectByExampleWithBLOBs(example);
        int records = essayMapper.countByExample(example);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",essays);//显示数据
        map.put("records",records);//记录数
        map.put("total",(records%rows==0)?(records/rows):(records/rows+1));//页数
        map.put("page",page);//页码
        return map;
    }



    @LogAnnotation("添加文章")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(Essay essay) {
        essay.setEssayId(UUID.randomUUID().toString().replace("-",""));
        essay.setEssayTime(new Date());
        //save有则添加
        essayRepository.save(essay);
        essayMapper.insertSelective(essay);
    }

    @LogAnnotation("删除文章")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void drop(Essay essay) {
        essayMapper.deleteByPrimaryKey(essay.getId());
        essayRepository.delete(essay);
    }

    @LogAnnotation("修改文章")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void alter(Essay essay) {
        essayMapper.updateByPrimaryKeySelective(essay);
        essayRepository.save(essay);
    }

    @Override
    public Map<String, Object> editForJq(String oper, Essay essay, String[] id) {
        return null;
    }

    @Override
    public Map<String, Object> findForJqSearchForPage(String searchString, String searchOper, String searchField, Integer rows, Integer page) {
        return null;
    }

    /**
     * 通过ElasticSearch高亮查询|分页查询
     * @param searchString 查询的值
     * @param page 页数
     * @param size 页面显示条数
     * @return 本页高亮数据
     */
    @Override
    public Map<String, Object> findByElasticSearchForPage(String searchString, Integer page, Integer size) {
        Map<String,Object> map = new HashMap<>();
        List<Essay> essays = new ArrayList<>();

        //创建高亮查询字段属性....
        HighlightBuilder.Field essayTittleField = new HighlightBuilder.Field("essayTittle")
                //关闭检索字段匹配
                .requireFieldMatch(false)
                //自定义高亮体样式
                .preTags("<span style='color:red'>").postTags("</span>");
        HighlightBuilder.Field authorField = new HighlightBuilder.Field("author")
                //关闭检索字段匹配
                .requireFieldMatch(false)
                //自定义高亮体样式
                .preTags("<span style='color:red'>").postTags("</span>");
        HighlightBuilder.Field essayContentField = new HighlightBuilder.Field("essayContent")
                //关闭检索字段匹配
                .requireFieldMatch(false)
                //自定义高亮体样式
                .preTags("<span style='color:red'>").postTags("</span>");

        //建立自定义查询的属性
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                //指定索引和类型
                .withIndices("cmfz").withTypes("essay")
                //指定查询的字段
                .withQuery(QueryBuilders.queryStringQuery("世界").field("essayTittle").field("author").field("essayContent"))
                //过滤，提高查询效率
                .withFilter(QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(searchString).field("essayTittle").field("author").field("essayContent")))
                //开启分页
                .withPageable(PageRequest.of(page-1,size))
                //开启多字段高亮查询
                .withHighlightFields(essayTittleField,authorField,essayContentField)
                .build();
        //调用自定义查询方法并将返回结果集接收
        AggregatedPage<Essay> essaysForSearch = elasticsearchTemplate.queryForPage(searchQuery, Essay.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                //创建返回结果
                List<Essay> essayList = new ArrayList<>();
                //获取结果集
                SearchHit[] hits = searchResponse.getHits().getHits();
                //获取总记录数
                long totalHits = searchResponse.getHits().getTotalHits();
                map.put("totalHits",totalHits);
                for (SearchHit hit : hits) {
                    //获取原始记录
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    Essay essay = null;
                    try {
                        essay = new Essay().setAuthor(sourceAsMap.get("author").toString())
                                .setEssayTittle(sourceAsMap.get("essayTittle").toString())
                                .setEssayContent(sourceAsMap.get("essayContent").toString())
                                .setEssayTime(new SimpleDateFormat("yyyy-MM-dd").parse(sourceAsMap.get("essayTime").toString()))
                                .setEssayId(sourceAsMap.get("essayId").toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //获取高亮的记录
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (highlightFields.containsKey("essayTittle")) {
                        essay.setEssayTittle(highlightFields.get("essayTittle").fragments()[0].toString());
                    } else if (highlightFields.containsKey("essayContent")) {
                        essay.setEssayContent(highlightFields.get("essayContent").fragments()[0].toString());
                    } else if (highlightFields.containsKey("author")) {
                        essay.setAuthor(highlightFields.get("author").fragments()[0].toString());
                    }
                    //添加到返回结果
                    essayList.add(essay);
                }//for (SearchHit hit : hits)结束

                return new AggregatedPageImpl<T>((List<T>) essayList);
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        //将返回结果集添加到List集合中
        essaysForSearch.forEach(e->essays.add(e));
        map.put("essays",essays);
        return map;
    }
}
