package henu.xmh.config;

import henu.xmh.realm.MyRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author XiaMH
 * Shiro认证授权的相关配置
 */
@Configuration
public class ShiroFilter {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        LinkedHashMap linkedHashMap = new LinkedHashMap();
        //设置静态资源可以匿名访问
        linkedHashMap.put("/static/**","anon");
        //设置文件可以访问
        linkedHashMap.put("/upload/**","anon");
        linkedHashMap.put("/admin/login","anon");
        //设置所有界面必须通过验证
        linkedHashMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        //设置登录界面
        shiroFilterFactoryBean.setLoginUrl("/back/login.jsp");
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        return shiroFilterFactoryBean;
    }

    /**
     * 将SecurityManager交由Spring工厂管理
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        //1.创建默认得DefaultWebSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义realm配置在shiro环境中
        securityManager.setRealm(myRealm());
        //配置shiro的缓存支持
        CacheManager cacheManager = new EhCacheManager();
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        return  myRealm;
    }
}
