package henu.xmh.controller;

import henu.xmh.pojo.*;
import henu.xmh.service.CfAlbumService;
import henu.xmh.service.CfImageService;
import henu.xmh.service.CmfzAppService;
import henu.xmh.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 持明法洲请求controller
 */
@RestController
@RequestMapping("cmfzapp")
public class CmfzController {
   @Autowired
   private CmfzAppService cmfzAppService;

    /**
     * 一级页面访问
     * @param userId  用户唯一标识
     * @param type 请求数据类型：(首页：all,闻：wen,思：si)
     * @param sub_type 上师言教:ssyj , 显密法要:xmfy
     * @return 相应页面数据
     */
    @GetMapping("findHome")
    public Map<String,Object> findHome(String userId,String type,String sub_type ){

        return cmfzAppService.findHome(userId,type,sub_type);
    }

    /**
     * 专辑详情
     * @param userId
     * @param albumId
     * @return
     */
    @GetMapping("findAlbumDetail")
    public Map<String,Object> findAlbumDetail(String userId,String albumId){
        return cmfzAppService.findCfAlbumDetail(albumId);
    }

    /**
     * 文章详情
     * @param userId
     * @param essayId
     * @return
     */
    @GetMapping("findOneEssay")
    public Essay findOneEssay(String userId,String essayId){
        return  cmfzAppService.findEssayDetail(essayId);
    }

    @GetMapping("login")
    public Map<String,Object> login(String mobilePhone,String password,String code){
        CfUser cfUser = null;
        Map<String,Object> map = new HashMap<>();
        try {
            if(mobilePhone!=null&&password!=null){//密码登录
                cfUser = cmfzAppService.loginByPwd(mobilePhone,password);
            }else if(mobilePhone!=null&&code!=null){//验证码登录
                cfUser = cmfzAppService.loginByCode(mobilePhone, code);
            }
            map.put("user",cfUser);//登录成功
            map.put("status",200);
        } catch (Exception e) {//登录失败
            e.printStackTrace();
            map.put("status",200);
            map.put("errorMsg",e.getMessage());
        }
        return map;
    }

    /**
     * 发送验证码接口
     * @param mobilePhone
     * @return
     */
    @GetMapping("sendMail")
    public Map<String,Object> sendMail(String mobilePhone){
        Map<String,Object> map = null;
        try {
            map = new HashMap<>();
            cmfzAppService.sendMail(mobilePhone);//向手机发送验证码
            map.put("status",200);
            map.put("message","验证码发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);
            map.put("message","验证码发送失败！");
        }
        return  map;
    }


    @GetMapping("register")
    public Map<String ,Object> register(String mobilePhone,String password){
        Map<String,Object> map = new HashMap<>();
        try {
            CfUser register = cmfzAppService.register(mobilePhone, password);
            map.put("status",200);//状态码
            map.put("userId",register.getUserId());//用户id
            map.put("password",register.getPassword());//用户密码
            map.put("mobilePhone",register.getMobilePhone());//手机号
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);
            map.put("errorMsg",e.getMessage());
        }
        return map;
    }

    @GetMapping("addCfUser")
    public Map<String,Object> addCfUser(CfUser cfUser){
        Map<String,Object> map = new HashMap<>();
        try {
            CfUser result = cmfzAppService.updateCfUser(cfUser);//返回值为更新后的信息
            map.put("status",200);
            map.put("user",result);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",200);
            map.put("message","更新失败！");
        }
        return map;
    }

    @GetMapping("addCourse")
    public Map<String,Object> addCourse(Course course){//添加课程
        return cmfzAppService.addCourse(course);
    }

    @GetMapping("findAllCourse")
    public Map<String,Object> findAllCourse(String userId){//查看课程
        return cmfzAppService.findAllCourse(userId);
    }

    @GetMapping("delCourse")
    public Map<String,Object> delCourse(Course course){//删除课程
        return cmfzAppService.dropCourse(course);
    }

    @GetMapping("findCounter")
    public Map<String,Object> findCounter(CourseCounter courseCounter){//查看计数器
        return cmfzAppService.findCounter(courseCounter);
    }
    @GetMapping("dropCounter")
    public Map<String,Object> dropCounter(CourseCounter courseCounter){//删除计数器
        return cmfzAppService.dropCounter(courseCounter);
    }

    @GetMapping("alterCounter")
    public Map<String,Object> alterCounter(CourseCounter courseCounter){//修改计数器
        return cmfzAppService.alterCounter(courseCounter);
    }

    @GetMapping("addCounter")
    public Map<String,Object> addCounter(CourseCounter courseCounter){//添加计数器
        return cmfzAppService.addCounter(courseCounter);
    }

    @GetMapping("aboutGuru")
    public List<Guru> aboutGuru(String userId,String guruId){
        return cmfzAppService.aboutGuru(userId, guruId);
    }

    @GetMapping("findAllGuru")
    public List<Guru> findAllGuru(String userId){
        return cmfzAppService.findGuru(userId);
    }

    @GetMapping("recommendCfUser")
    public List<CfUser> recommedCfUser(String userId){
        return cmfzAppService.recommendCfUser(userId);
    }

}
