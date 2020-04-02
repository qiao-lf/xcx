package henu.xmh.controller;

import henu.xmh.service.CfAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class CfAdminController {
    @Autowired
    private CfAdminService cfAdminService;

    @RequestMapping("login")
    public String login(String username, String password, HttpServletRequest request){
        try{
            //1.创建令牌
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            //2.通过SecurityUtils创建subject主体
            Subject subject = SecurityUtils.getSubject();
            //3.通过subject调用login认证方法
            subject.login(usernamePasswordToken);
            return "success";//登陆成功
        }catch (Exception e){
            return e.getMessage();//登录失败
        }
    }

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Boolean _search,String searchString,String searchField,String searchOper,Integer rows,Integer page){
        Map<String, Object> map = null;
        if(!_search){
             map = cfAdminService.findCfAdminRolesAndResourcesForPage(rows, page);
        }
        return map;
    }
}
