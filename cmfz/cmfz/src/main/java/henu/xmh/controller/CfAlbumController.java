package henu.xmh.controller;

import henu.xmh.pojo.CfAlbum;
import henu.xmh.service.CfAlbumService;
import henu.xmh.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("album")
public class CfAlbumController {

    @Autowired
    private CfAlbumService cfAlbumService;

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Boolean _search,String searchString,String searchField,String searchOper,Integer rows,Integer page){
        Map<String,Object> map = new HashMap<>();
        if(!_search){
            map = cfAlbumService.findAllForPage(page, rows);
        }else {//搜索相关

        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(String albumId, MultipartFile albumCover, HttpServletRequest request) throws IOException {
        //工具类上传
        String coverUrl = FileUploadUtil.upload(albumCover, request, "/upload/album");
        //将文件的相关信息更新到数据库
        CfAlbum cfAlbum = new CfAlbum();
        cfAlbum.setAlbumCover(coverUrl).setAlbumId(albumId);
        cfAlbumService.alterCfAlnum(cfAlbum);//同步信息到数据库

    }
    @RequestMapping("alter")
    public Map<String,Object> alter(String oper, CfAlbum cfAlbum,String[] id){
        Map<String, Object> map = cfAlbumService.editForJq(oper, cfAlbum, id);
        return map;
    }
}
