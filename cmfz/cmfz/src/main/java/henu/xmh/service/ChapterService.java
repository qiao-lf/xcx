package henu.xmh.service;

import henu.xmh.pojo.Chapter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ChapterService {
    //查询专辑下的所有章节并分页
    Map<String,Object> findOneChaptersByAlbumIdForPage(String albumId, Integer page, Integer rows);

    void add(Chapter chapter);

    void drop(Chapter chapter);

    void alter(Chapter chapter);

    void drop(List<String> id);

    /**
     * 文件的add ，alter,del
     * @param oper 操作
     * @param chapter 文件信息
     * @param id 批量删除
     * @param albumId 专辑的id
     * @param request 请求
     * @return
     */
    Map<String,Object> editForJq(String oper, Chapter chapter, String[] id, String albumId, HttpServletRequest request);

    //搜索该专辑下的章节音频并分页
    Map<String,Object> findOneChaptersForSearchByAlbumIdForPage(
            String searchString,String searchOper,String searchField,Integer rows,Integer page
    );
}
