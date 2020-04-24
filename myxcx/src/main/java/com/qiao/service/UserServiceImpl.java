package com.qiao.service;

import com.qiao.dao.UserDao;
import com.qiao.entity.User;
import com.qiao.util.GetUUIDUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map login(String phone, String password, HttpSession session) {
        HashMap hm = new HashMap();
        //根据账号查询用户
        User user = new User();
        user.setPhone(phone);
        User user1 = userDao.selectOne(user);
        if(user1==null){
            hm.put("status","-200");
            hm.put("message","输入的账号不存在");
            return hm;
        }
        user.setPassword(password);
        User user2 = userDao.selectOne(user);
        if(user2==null){
            hm.put("status","-200");
            hm.put("message","输入的密码错误");
            return  hm;
        }
        session.setAttribute("LocalUser",user2);
        hm.put("status", "200");
        hm.put("user",user2);
        return hm;
    }

    @Override
    public Map register(User user) {
        HashMap hashMap = new HashMap();
        try {
            String uid = GetUUIDUtils.getUUID32();
            user.setId(uid).setCreatedata(new Date()).setStatus("激活");
            userDao.insert(user);
            User user1 = userDao.selectOne(new User().setPhone(user.getPhone()));
            hashMap.put("status", "200");
            hashMap.put("message", "注册成功");
            hashMap.put("user", user1);
        } catch (Exception e) {
            hashMap.put("status", "-200");
            hashMap.put("message", "注册失败");
        }finally {
            return hashMap;
        }

    }

    @Override
    public Map updateUser(User user) {
        HashMap hashMap = new HashMap();
        try{
            int i = userDao.updateByPrimaryKeySelective(user);
            User user1 = userDao.selectOne(new User().setId(user.getId()));

            hashMap.put("status", "200");
            hashMap.put("message", "修改成功 ");
            hashMap.put("user",user1);
        }catch(Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","修改失败");
        }finally{
            return hashMap;
        }

    }

    @Override
    public Map freezeUser(User user) {
        HashMap hashMap = new HashMap();
        if(user.getStatus().equals("冻结")){
            try{
                user.setStatus("正常");
                System.out.println("------------------");
                System.out.println(user);
                userDao.updateByPrimaryKeySelective(user);
                hashMap.put("status", "200");
                hashMap.put("message", "解冻成功 ");
            }catch(Exception e){
                hashMap.put("status","-200");
                hashMap.put("message","解冻失败");
            }finally{
                return hashMap;
            }
        }else {
            try{
                user.setStatus("冻结");
                System.out.println("------------------");
                System.out.println(user);
                userDao.updateByPrimaryKeySelective(user);
                hashMap.put("status", "200");
                hashMap.put("message", "冻结成功 ");
            }catch(Exception e){
                hashMap.put("status","-200");
                hashMap.put("message","冻结失败");
            }finally{
                return hashMap;
            }
        }
    }

    @Override
    public Map showAllUserByPage(Integer page, Integer rows) {
        HashMap hashMap=new HashMap();
        //分页查询
        List<User> userList = userDao.selectByRowBounds(new User(), new RowBounds((page - 1) * rows, rows));
        int records=userDao.selectCount(new User());  //计算总数
        int total=records % rows == 0 ? records / rows :records /rows +1; //三木运算
        hashMap.put("rows",userList);
        hashMap.put("page",page);
        hashMap.put("records",records);
        hashMap.put("total",total);
        return hashMap;
    }

    @Override
    public User findUser(String id) {
        User user = userDao.selectOne(new User().setId(id));
        return user;
    }
}
