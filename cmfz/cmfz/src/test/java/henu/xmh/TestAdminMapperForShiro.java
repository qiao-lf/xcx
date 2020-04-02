package henu.xmh;

import henu.xmh.dao.CfAdminMapper;
import henu.xmh.pojo.CfAdmin;
import henu.xmh.pojo.Resource;
import henu.xmh.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author XiaMH
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAdminMapperForShiro {

    @Autowired
    private CfAdminMapper cfAdminMapper;

    @Test
    public void testCfAdmin(){
        CfAdmin xmh = cfAdminMapper.selectCfAdminRolesAndResourcesByUsername("xmh");
        List<Role> roles = xmh.getRoles();
        roles.forEach(r->{
            System.out.println("============"+r.getRoleName()+"============");
            List<Resource> resources = r.getResources();
            resources.forEach(re-> System.out.println(re));
        });

    }

}
