package com.qiao.realm;

import com.qiao.dao.AdminDao;
import com.qiao.entity.Admin;
import com.qiao.entity.Resource;
import com.qiao.entity.Roles;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AdminDao adminDao;

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取主题信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //通过主身份信息查询数据库  来查询用户的权限信息 （5表联查）
        Admin admin = adminDao.queryAdminInfo(primaryPrincipal);//通过主身份信息查询管理员
        //将查询出来的角色信息和权限信息转换成List<String>集合
        ArrayList roles = new ArrayList();
        ArrayList resources = new ArrayList();
        List<Roles> rolesList = admin.getRoles();
        for (Roles role : rolesList) {
            roles.add(role.getRoleName());
            for (Resource res : role.getResources()) {
                resources.add(res.getResourceName());
            }
        }
        //将角色授权信息交给授权器管理
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(resources);
        return authorizationInfo;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取主体信息
        String principal = (String) token.getPrincipal();
        //通过数据库查询改主体信息
        Admin admin = new Admin().setUsername(principal);
        Admin admin1 = adminDao.selectOne(admin);
        //利用SimpleAuthenticationInfo 做认证
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin1.getUsername(), admin1.getPassword(), this.getName());
        return authenticationInfo;
    }
}
