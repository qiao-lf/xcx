package henu.xmh.service;

import henu.xmh.pojo.CfUser;
import henu.xmh.pojo.MapVo;

import java.util.List;
import java.util.Map;

public interface CfUserService {

    Map<String,Object>  findAllGorPage(Integer page,Integer rows);

    Map<String,Object> alterForJq(String oper, CfUser cfUser,String[] id);

    Map<String,Object> findRegisterDevelopment();

    List<MapVo> findCountUserLocation();

    Integer findCountForInDayValue(Integer dayValue,String sex);

    CfUser findOneByMobilePhone(String mobilePhone);

    void alter(CfUser cfUser);

    void drop(CfUser cfUser);

    void add(CfUser cfUser);
}
