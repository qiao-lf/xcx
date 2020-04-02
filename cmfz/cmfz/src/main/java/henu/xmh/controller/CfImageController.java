package henu.xmh.controller;

import com.alibaba.excel.EasyExcel;
import henu.xmh.pojo.CfImage;
import henu.xmh.service.CfImageService;
import henu.xmh.util.POIExcelFOrCfImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("wheel")
public class CfImageController {

    @Autowired
    private CfImageService cfImageService;

    /**
     *
     * @param _search 是否为搜索
     * @param searchField 字段名
     * @param searchString 字段值
     * @param searchOper 条件
     * @param rows 条数
     * @param page 当前页码
     * @return
     */
    @RequestMapping("findAll")
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows, Integer page){
        Map<String,Object> maps = new HashMap<>();
        List<CfImage> lists = new ArrayList<>();
        Integer records = null;
        Integer total = null;
        if(!_search){//如果是查询所有
            records = cfImageService.findCountForPage();//记录数
            total = (records%rows==0)?(records/rows):(records/rows+1);//页数
            lists = cfImageService.finaAllCfImageForPage(page, rows);
        }else {//条件搜索
            records = cfImageService.findCountForCfIamgeByJqSerach(searchField,searchString,searchOper);
            total = (records%rows==0)?(records/rows):(records/rows+1);//页数
            lists=cfImageService.findCfIamgeByJqSerach(searchField,searchString,searchOper,rows,page);
        }
        maps.put("records",records);
        maps.put("page",page);
        maps.put("total",total);
        maps.put("rows",lists);
        return maps;
    }

    //上传
    @RequestMapping("upload")
    public void upload(String imageId,MultipartFile newName, HttpServletRequest request) throws IOException {//文件上传
        MultipartFile url = newName;
        //获取绝对路径
        String realPath = request.getRealPath("/upload");
        //获取原始文件名
        String originalFilename = url.getOriginalFilename();
        //判断当天目录是否存在（没有则创建）
        String dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//当天的目录
        File file = new File(realPath, dateDir);
        if(!file.exists())file.mkdirs();//递归创建目录
        //处理文件文件名
        String newFileName = new Date().getTime()+"_"+originalFilename;
        //上传文件
        url.transferTo(new File(file,newFileName));
        //获取协议头
        String scheme = request.getScheme();//协议头为多种：如：TCP,FTP,HTTP,https....
        //获取ip地址
        String localHostIp = InetAddress.getLocalHost().toString();//获取的是本机的IP地址
        //对IP地址进行截取处理
        String ip = localHostIp.split("/")[1];//截取的为IP地址
        //获取项目名
        String contextPath = request.getContextPath();
        //获取端口号
        int serverPort = request.getServerPort();
        //拼接网络地址
        String imgUrl = scheme+"://"+ip+":"+serverPort+contextPath+"/upload/"+dateDir+"/"+newFileName;
        //保存到数据库
        CfImage cfImage = new CfImage();
        cfImage.setImageId(imageId).setNewName(imgUrl).setImageDir(dateDir).setOrgName(originalFilename);
        cfImageService.alterCfImage(cfImage);

    }

    @RequestMapping("alter")
    public Map alter(CfImage cfImage,String oper,String[] id){
        cfImage.setNewName(null);
        Map<String, Object> result = cfImageService.alterForJq(cfImage, oper,id);
        return  result;
    }

    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            List<CfImage> all = cfImageService.findAll();
            String fileName = new Date().getTime()+"_"+"imagPOI.xls";
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
            EasyExcel.write(outputStream,CfImage.class).sheet().doWrite(cfImageService.findAll());
        } finally {//释放资源
            if(outputStream!=null){
                outputStream.close();
            }
        }
    }
}
