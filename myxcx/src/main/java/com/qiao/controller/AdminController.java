package com.qiao.controller;

import com.qiao.entity.Admin;
import com.qiao.service.AdminServicce;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("ad")
public class AdminController {
    @Autowired
    private AdminServicce adminServicce;


    @ResponseBody
    @RequestMapping("login")
    public String login(HttpServletRequest request,Admin admin, String code) {
        //封装令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        //通过util嘞获取subject主题
        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        Admin ads = adminServicce.login(admin);
        if (ads == null) {
            return "账号或密码输入有误";
        }
        try {
            subject.login(usernamePasswordToken);
            session.setAttribute("LocalAdmin", ads);
            return null;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "权限不足";
        }
    }
    @ResponseBody
    @RequestMapping("exit")
    public void exit(HttpServletRequest request) {
        // 获取主体信息
        Subject subject = SecurityUtils.getSubject();
        // 执行登出方法
        subject.logout();
        HttpSession session = request.getSession();
        session.removeAttribute("LocalAdmin");
    }
}
