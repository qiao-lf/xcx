package henu.xmh.service.impl;

import henu.xmh.annotation.AddCache;
import henu.xmh.annotation.DelCache;
import henu.xmh.annotation.LogAnnotation;
import henu.xmh.dao.ChapterMapper;
import henu.xmh.pojo.Chapter;
import henu.xmh.pojo.ChapterExample;
import henu.xmh.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 查询该专辑下的所有章节并分页
     * @param albumId 章节id
     * @param page 页码
     * @param rows  页面大小
     * @return
     */
    @AddCache
    @Override
    public Map<String, Object> findOneChaptersByAlbumIdForPage(String albumId, Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<>();
        ChapterExample example = new ChapterExample();
        example.setBeginValue((page-1)*rows);
        example.setPageSize(rows);
        ChapterExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumIdEqualTo(albumId);
        List<Chapter> chapters = chapterMapper.selectByExample(example);
        int records = chapterMapper.countByExample(example);
        map.put("rows",chapters);//页面内容
        map.put("records",records);//记录数
        map.put("total",(records%rows==0)?(records/rows):(records/rows+1));//页数
        map.put("page",page);//页码
        return map;
    }

    @DelCache
    @LogAnnotation("添加音频")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(Chapter chapter) {
        chapterMapper.insertSelective(chapter);
    }

    @DelCache
    @LogAnnotation("删除音频数据记录")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void drop(Chapter chapter) {
        chapterMapper.deleteByPrimaryKey(chapter.getChapterId());
    }

    @DelCache
    @LogAnnotation("修改音频")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void alter(Chapter chapter) {
        chapterMapper.updateByPrimaryKeySelective(chapter);
    }

    @DelCache
    @LogAnnotation("批量删除音频记录")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void drop(List<String> id) {//批量删除
        chapterMapper.deleteByIdList(id);
    }

    /**
     * jqgrid的章节的add,edit,del业务方法
     * @param oper 操作
     * @param chapter 提交的章节信息
     * @param id 批量删除时的id
     * @return
     */
    @DelCache
    @Override
    public Map<String, Object> editForJq(String oper, Chapter chapter, String[] id, String albumId, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        if("edit".equals(oper)){//修改
            chapter.setChapterId(chapter.getId());
            chapter.setVoiceurl(null);
            alter(chapter);
            map.put("chapterId",chapter.getChapterId());
        }else if("del".equals(oper)){//删除
            System.out.println("this is drop"+chapter);
            if(chapter.getChapterId()!=null){//删除单个
                delMapsFile(chapter,request);//删除系统文件
                drop(chapter);//删除系统数据
                map.put("chapterId",chapter.getChapterId());
            }else {//批量删除
                //批量删除文件
                for (String chapterId : id) {
                    Chapter chapter1 = chapterMapper.selectByPrimaryKey(chapterId);
                    delMapsFile(chapter1,request);//删除系统文件
                }
                drop(Arrays.asList(id));//删除数据信息
            }
        }else if("add".equals(oper)){//添加
            chapter.setChapterId(UUID.randomUUID().toString().replace("-","")).setAlbumId(albumId);
            add(chapter);
            map.put("chapterId",chapter.getChapterId());
        }
        map.put("statsus",200);
        return map;
    }
    @DelCache
    @LogAnnotation("添加音频文件")
    private void delMapsFile(Chapter chapter,HttpServletRequest request){
        //删除系统文件
        String fileNewName = chapter.getVoiceurl().split("/")[6];
        String realPath = request.getRealPath("/upload/audio");
        File file = new File(realPath + "/" + fileNewName);
        if(file.exists()){
            file.delete();//删除文件
            System.out.println(fileNewName+"删除成功！");
        }
    }
    /**
     * 搜索该专辑下的章节信息
     * @param searchString 搜索的属性值
     * @param searchOper 搜索条件
     * @param searchField 搜索属性
     * @param rows 页面大小
     * @param page 页码
     * @return
     */
    @AddCache
    @Override
    public Map<String, Object> findOneChaptersForSearchByAlbumIdForPage(
            String searchString, String searchOper, String searchField, Integer rows, Integer page) {
        return null;
    }
}
