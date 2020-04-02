package henu.xmh.realm;

import henu.xmh.dao.CfAdminMapper;
import henu.xmh.pojo.CfAdmin;
import henu.xmh.pojo.Resource;
import henu.xmh.pojo.Role;
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

/**
 * @author XiaMH
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private CfAdminMapper cfAdminMapper;

    //1.授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取主体信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //2.通过主身份信息查询数据库来查询用户的权限信息（5表联查）
        CfAdmin cfAdmin = cfAdminMapper.selectCfAdminRolesAndResourcesByUsername(primaryPrincipal);
        //3.将查询出来的角色信息和权限信息转换成List<String>集合
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<String> permissions = new ArrayList<>();
        List<Role> rolesIn = cfAdmin.getRoles();
        rolesIn.forEach(r->{
            roles.add(r.getRoleName());
            List<Resource> resources = r.getResources();
            resources.forEach(re->{
                permissions.add(re.getResourceName());
            });
        });
        //4.将角色授权信息交给授权器管理
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    //2.认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取主体信息
        String principal = (String) authenticationToken.getPrincipal();
        //2.通过数据库查询改主体信息
        CfAdmin cfAdmin = cfAdminMapper.selectByPrimaryKey(principal);
        //3.利用SimpleAuthenticationInfo来做认证
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(cfAdmin.getUsername(),cfAdmin.getPassword(),this.getName());
        return authenticationInfo;
    }
}
