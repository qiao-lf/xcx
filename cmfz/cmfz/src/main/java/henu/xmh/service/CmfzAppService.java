package henu.xmh.service;

import henu.xmh.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CmfzAppService {

    /**
     * 首页显示数据业务方法
     * @param userId
     * @param type
     * @param sub_type
     * @return
     */
    Map<String,Object> findHome(String userId,String type,String sub_type );

    Map<String,Object> findCfAlbumDetail(String albumId);

    Essay findEssayDetail(String essayId);

    String sendMail(String mobilePhone);

    CfUser loginByPwd(String mobilePhone, String password);//密码登录

    CfUser loginByCode(String mobilePhone,String code);//验证码登录

    CfUser register(String mobilePhone,String password);//用户注册

    CfUser updateCfUser(CfUser cfUser);//补充个人资料

    Map<String,Object> addCourse(Course course);//添加课程

    Map<String,Object> findAllCourse(String userId);//查看课程

    Map<String,Object> dropCourse(Course course);//删除课程

    Map<String,Object> dropCounter(CourseCounter courseCounter);//删除计数器

    Map<String,Object> findCounter(CourseCounter courseCounter);//查看计数器

    Map<String,Object> alterCounter(CourseCounter courseCounter);//修改计数器数

    Map<String,Object> addCounter(CourseCounter courseCounter);//添加计数器

    List<Guru> aboutGuru(String userId,String guruId);//关注上师

    List<Guru> findGuru(String userId);//展示上师

    List<CfUser> recommendCfUser(String userId);//金刚道友
}
