package henu.xmh;

import henu.xmh.dao.EssayMapper;
import henu.xmh.pojo.Essay;
import henu.xmh.pojo.EssayExample;
import henu.xmh.repository.EssayRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author XiaMH
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEsForEssay {
    @Autowired
    private EssayRepository essayRepository;

    @Test
    public void test1(){
        Essay essay = new Essay().setEssayId(UUID.randomUUID().toString().replace("-",""))
                .setEssayTime(new Date()).setEssayContent("这是一年文章").setEssayTittle("背影").setAuthor("李志");
        Essay save = essayRepository.save(essay);
        System.out.println(save);
    }

    @Autowired
    private EssayMapper essayMapper;
    @Test
    public void test2(){
        EssayExample example = new EssayExample();
        List<Essay> essays = essayMapper.selectByExampleWithBLOBs(example);
        essayRepository.saveAll(essays);
    }


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 自定义查询
     */
    @Test
    public void test3(){
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
                .withFilter(QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery("世界").field("essayTittle").field("author").field("essayContent")))
                //开启分页
                .withPageable(PageRequest.of(0,5))
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
        System.out.println(map);
    }
}
