package henu.xmh.service;

import henu.xmh.pojo.CfImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CfImageService {
    //添加图片
    void addCfImage(CfImage cfImage);
    //删除图片(真删除)
    void dropImageById(String imageId);
    //修改图片
    void alterCfImage(CfImage image);
    //查询所有图片
    List<CfImage> finaAllCfImageForPage(Integer currentPageNum, Integer pageSize);
    //获得当前记录数
    Integer findCountForPage();

    //jqgird的写操作
    Map<String,Object>  alterForJq(CfImage cfImage, String oper,String[] id);

    //搜索
    List<CfImage> findCfIamgeByJqSerach(String searchField,String searchString,String searchOper,Integer rows, Integer page);
    //获取搜索的记录数
    Integer findCountForCfIamgeByJqSerach(String searchField,String searchString,String searchOper);

    //文件上传

    List<CfImage> findAll();

    List<CfImage> findCfImageForApp();
}
