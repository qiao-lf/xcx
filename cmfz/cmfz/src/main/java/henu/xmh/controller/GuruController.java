package henu.xmh.controller;

import henu.xmh.pojo.Guru;
import henu.xmh.service.GuruService;
import henu.xmh.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("guru")
public class GuruController {
    @Autowired
    private GuruService guruService;

    @RequestMapping("findAllForJq")
    public void findAllForJg(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Guru> all = guruService.findAll();
        PrintWriter writer = response.getWriter();
        writer.print("<select name='author'>");
        all.forEach(a->{
            writer.print("<option value='"+a.getGuruName()+"'>"+a.getGuruName()+"</option>");
        });
        writer.print("</select>");
        writer.flush();
        writer.close();
    }

    @RequestMapping("findAll")
    public List<Guru> findAll(){
        return guruService.findAll();
    }

    @RequestMapping("findAllForGuru")
    public Map<String,Object> findAllForGuru(Boolean _search,String searchString,String searchField,String searchOper,Integer rows,Integer page){
        Map<String,Object> map = new HashMap<>();
        if(!_search){
            map = guruService.findAllForPage(page,rows);
        }
        return map;
    }

    @RequestMapping("alter")
    public Map<String,Object> alterForJq(String oper,Guru guru,String[] id){
        return guruService.alterForJq(oper, guru, id);
    }

    @RequestMapping("upload")
    public void upload(String guruId,MultipartFile guruImage,HttpServletRequest request){
        System.out.println(guruImage);
        String httpUrl = FileUploadUtil.upload(guruImage, request, "/upload/guru");
        System.out.println("上师头像上传成功："+httpUrl);
        //更新数据库中的url
        Guru guru = new Guru().setGuruId(guruId).setGuruImage(httpUrl);
        guruService.alter(guru);
    }
}
