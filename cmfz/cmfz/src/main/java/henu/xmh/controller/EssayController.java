package henu.xmh.controller;

import henu.xmh.pojo.Essay;
import henu.xmh.service.EssayService;
import henu.xmh.util.FileUploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("essay")
public class EssayController {
    @Autowired
    private EssayService essayService;

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Boolean _search,String searchString,String searchField,String searchOper,Integer rows,Integer page){
        Map<String ,Object> map = new HashMap<>();
        if(!_search){
            map = essayService.findAllEssayForPage(rows,page);
        }else {//搜索

        }
        return map;
    }
    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile imgFile, HttpServletRequest request){
        System.out.println(imgFile);
        Map<String, Object> map = new HashMap<>();
        String upload = FileUploadUtil.upload(imgFile, request, "/upload/essay");
        map.put("url",upload);
        map.put("error",0);
        return  map;
    }
    @RequestMapping("add")
    public Map<String,Object> add(Essay essay){
        essayService.add(essay);
        Map<String,Object> map = new HashMap<>();
        map.put("key","successs");
        return map;
    }
    @RequestMapping("alter")
    public Map<String,Object> alter(Essay essay){
        essayService.alter(essay);
        Map<String,Object> map = new HashMap<>();
        map.put("key","successs");
        return map;
    }

    @RequestMapping("imgManager")
    public Map<String,Object> imgManager(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        String realPath = request.getRealPath("/upload/essay");
        File file = new File(realPath);
        ArrayList<Object> file_list = new ArrayList<>();
        File[] files = file.listFiles();//获取文件夹下的所有文件
        for (File file1 : files) {
            Map<String,Object> ele = new HashMap<>();
            ele.put("is_dir",false);//不是目录
            ele.put("has_file",false);//
            ele.put("filesize",file1.length());
            ele.put("is_photo",true);//图片
            //获取文件后缀（类型）
            String extension = FilenameUtils.getExtension(file1.getName());
            ele.put("filetype",extension);//文件类型
            ele.put("filename",file1.getName());//文件名
            String timeLong = file1.getName().split("_")[0];//时间long类型
            String format = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date(Long.valueOf(timeLong)));
            ele.put("datetime",format);//处理上传时间
            file_list.add(ele);//添加此文件元素的信息
        }
        map.put("current_url",request.getContextPath()+"/upload/essay/");//文件所在目录的相对路径
        map.put("total_count",file_list.size());//文件数量
        map.put("file_list",file_list);//文件信息
        return map;
    }

    @RequestMapping("search")
    public Map search(String searchString,Integer page,Integer size){
        Map<String, Object> byElasticSearchForPage = essayService.findByElasticSearchForPage(searchString, page, size);
        return byElasticSearchForPage;
    }
}
