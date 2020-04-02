package henu.xmh.controller;

import henu.xmh.pojo.CfUser;
import henu.xmh.pojo.MapVo;
import henu.xmh.service.CfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class CfUserController {

    @Autowired
    private CfUserService cfUserService;

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Boolean _search,String searchString,String searchOper,
                                      String searchField,Integer rows,Integer page) {
        Map<String, Object> map = new HashMap<>();
        if (!_search) {
            map = cfUserService.findAllGorPage(page, rows);
        }
        return map;
    }

    @RequestMapping("alter")
    public Map<String,Object> alterForJq(String oper, CfUser cfUser,String[] id){
        return cfUserService.alterForJq(oper, cfUser, id);
    }

    @RequestMapping("findRegisterDevelopment")
    public Map<String,Object> findRegisterDevelopment(){
        return cfUserService.findRegisterDevelopment();
    }

    @RequestMapping("userLocation")
    public List<MapVo> userLocation(){
        return cfUserService.findCountUserLocation();
    }
}
