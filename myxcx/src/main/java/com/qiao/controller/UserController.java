package com.qiao.controller;

import com.qiao.entity.User;
import com.qiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("us")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("showAllUserByPage")
    public Map showAllUserByPage(Integer page, Integer rows){
        Map map = userService.showAllUserByPage(page, rows);
        return  map;
    }
    @ResponseBody
    @RequestMapping("updateStatus")
    public Map updateStatus(String id){
        //System.out.println(id);
       User user = userService.findUser(id);
        HashMap hashMap = new HashMap();
        if (user.getId().isEmpty()){//对象ID为主键  必定不为空 因此判断主键就可以得知
            hashMap.put("status", "-200");
            hashMap.put("message", "数据传输失败了 ");
           return hashMap;
        }
        Map map = userService.freezeUser(user);
        return map;
    }

}
