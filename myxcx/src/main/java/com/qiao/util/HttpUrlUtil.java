package com.qiao.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class HttpUrlUtil {
    /*
        1. 获取上传文件的网络路径
           2. 上传文件
        针对于不同的文件 需要设置不同的文件夹存放那些文件
     */

    public static String getHttpUrl(MultipartFile file, HttpServletRequest request, HttpSession session, String dir) {
        /**
         * MultipartFile file  上传的文件
         * String dir   路径
         */
        //获取路径   相对路径求绝对路径
        String realPath = session.getServletContext().getRealPath(dir);
        System.out.println(realPath);
        //判断路径文件夹是否存在   存放图片    存放音频   音频 在图片的子包中
        File f = new File(realPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        //防止重名操作
        String originalFilename = file.getOriginalFilename();
        originalFilename = new Date().getTime() + "_" + originalFilename;
        try {
            file.transferTo(new File(realPath, originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 相对路径 : ../XX/XX/XX.jpg
        // 网络路径 : http://IP:端口/项目名/文件存放位置
        String http = request.getScheme();
        String localHost = null;
        try {
            localHost = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        // 网络路径拼接
        String uri = http + "://" + localHost.split("/")[1] + ":" + serverPort + contextPath + dir + originalFilename;
        return uri;
    }

}
