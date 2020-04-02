package henu.xmh.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

public class FileUploadUtil {
    /**
     * 返回网络路径
     *
     * @param albumCover 文件名
     * @param request
     * @param path 上传的相对路径
     * @return
     */
    public static String upload( MultipartFile albumCover, HttpServletRequest request,String path){
        try {
            //获取绝对路径
            String realPath = request.getRealPath(path);
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdirs();//创建文件
            }
            //获取文件原始名并转换文件名避免重复
            String originalFilename = albumCover.getOriginalFilename();
            String newFileName = new Date().getTime()+"_"+originalFilename;//新文件名
            //上传文件到指定路径
            albumCover.transferTo(new File(realPath,newFileName));//将文件复制到指定路径
            //拼接文件的网络路劲http://ip:port/contextpath/realParh/newFileName
            String http = request.getScheme();        //获取协议头
            int port = request.getServerPort(); //获取端口
            String ip = InetAddress.getLocalHost().toString().split("/")[1];//获取IP地址（截取掉用户名）
            String contextPath = request.getContextPath();//获取项目名
            String coverUrl = http+"://"+ip+":"+port+contextPath+""+path+"/"+newFileName;
            return coverUrl;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传异常！");
        }
    }
}
