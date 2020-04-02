package henu.xmh.service;

import henu.xmh.pojo.CfAdmin;

import java.util.List;
import java.util.Map;

public interface CfAdminService {
    void login(String username,String password);

    Map<String,Object> findCfAdminRolesAndResourcesForPage(Integer rows,Integer page);

    Map<String,Object> alterCfAdminForJq(String oper, CfAdmin cfAdmin);
}
