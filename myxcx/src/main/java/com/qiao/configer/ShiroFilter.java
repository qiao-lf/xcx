package com.qiao.configer;

import com.qiao.realm.MyRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

//配置类
@Configuration
public class ShiroFilter {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        // 可以 通过ShiroFilterFactoryBean 配置整个shiro 过滤器
        // 3. 创建一个shiroFilterFactoryBean对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 5. 配置过滤器链 注意 1.使用LinkedHashMap 2.要求将anon过滤器声明写在前面
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        // 注意 : 如出现静态资源拦截问题 会network 显示 302 错误
        //设置静态资源可以匿名访问
        linkedHashMap.put("/static/**", "anon");
        //linkedHashMap.put("/echarts/**", "anon");
       // linkedHashMap.put("/upload/**", "anon");
        linkedHashMap.put("/kindeditor/**", "anon");
        // 将登陆方法 放行
        linkedHashMap.put("/ad/login", "anon");
        //拦截其他的页面
        linkedHashMap.put("/**", "authc");
        // 6. 将过滤器链交由shiroFilterFactoryBean管理
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        // 7. 设置登录Url
        shiroFilterFactoryBean.setLoginUrl("/html/login.jsp");
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        return shiroFilterFactoryBean;
    }

    // 创建SecurityManager对象 交给spring工厂管理
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        CacheManager cacheManager = new EhCacheManager();
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    // 创建myRealm对象 交给spring工厂管理
    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }


}
