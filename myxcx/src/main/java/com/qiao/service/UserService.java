package com.qiao.service;

import com.qiao.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {

    public Map login(String phone, String password, HttpSession session); //登录
    public Map  register(User user);//注册

    public Map updateUser(User user);//用户自己的修改

    public Map freezeUser(User user);//管理员冻结用户

    public Map showAllUserByPage(Integer page, Integer rows);  //分页展示全部的用户

    public User findUser(String id);




}
