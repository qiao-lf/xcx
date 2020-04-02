package henu.xmh.service;

import henu.xmh.annotation.LogAnnotation;
import henu.xmh.pojo.CfAlbum;

import java.util.List;
import java.util.Map;

public interface CfAlbumService {
    //分页查询

    Map<String,Object> findAllForPage(Integer page,Integer rows);

    //添加
    void addCfAlbum(CfAlbum cfAlbum);
    //删除
    void dropCfAlbum(CfAlbum cfAlbum);
    //修改
    void alterCfAlnum(CfAlbum cfAlbum);

    //jqgrid的编辑相关的业务方法（添加，删除，修改）
    Map<String,Object> editForJq(String oper,CfAlbum cfAlbum,String[] id);

    //jqgrid的搜索相关业务方法
    Map<String,Object> findCfAlbumForJqSearch(Boolean _search,String searchField,String searchString
            ,String searchOper,Integer page,Integer rows);


}
