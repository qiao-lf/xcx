package henu.xmh.service.impl;

import henu.xmh.annotation.LogAnnotation;
import henu.xmh.annotation.UserRegister;
import henu.xmh.dao.CfUserMapper;
import henu.xmh.pojo.CfUser;
import henu.xmh.pojo.CfUserExample;
import henu.xmh.pojo.MapVo;
import henu.xmh.service.CfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CfUserServiceImpl implements CfUserService {
    @Autowired
    private CfUserMapper cfUserMapper;

    /**
     * Jqgrid分页
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Map<String, Object> findAllGorPage(Integer page, Integer rows) {
        CfUserExample cfUserExample = new CfUserExample();
        cfUserExample.setBeginValue((page-1)*rows);
        cfUserExample.setPageSize(rows);
        List<CfUser> cfUsers = cfUserMapper.selectByExample(cfUserExample);
        Integer records = cfUserMapper.countByExample(cfUserExample);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",cfUsers);
        map.put("records",records);
        map.put("page",page);
        map.put("total",(records%rows==0)?(records/rows):(records/rows+1));
        return map;
    }

    /**
     * Jgrid的写操作相关业务方法
     * @param oper
     * @param cfUser
     * @param id
     * @return
     */
    @UserRegister("Jqgird修改用户状态")
    @LogAnnotation("Jqgird修改用户状态")
    @Override
    public Map<String, Object> alterForJq(String oper, CfUser cfUser, String[] id) {
        Map<String, Object> map = new HashMap<>();
        if("add".equals(oper)){//添加
            cfUser.setUserId(UUID.randomUUID().toString().replace("-",""));
            add(cfUser);
            map.put("userId",cfUser.getUserId());
        }else if("edit".equals(oper)){//修改
            if("正常".equals(cfUser.getUserStatus())) cfUser.setUserStatus("2");
            else cfUser.setUserStatus("1");
            alter(cfUser);
            map.put("userId",cfUser.getUserId());
        }
        map.put("status",200);
        return map;
    }

    /**
     * 返回用户的注册趋势信息
     * @return
     */
    @Override
    public Map<String, Object> findRegisterDevelopment() {
        Map<String, Object> map = new HashMap<>();
        List<Integer> man = new ArrayList<>();
        List<Integer> woman = new ArrayList<>();
        //男性注册趋势
        Integer man1 = findCountForInDayValue(1, "1");
        Integer man7 = findCountForInDayValue(7, "1");
        Integer man30 = findCountForInDayValue(30, "1");
        Integer man365 = findCountForInDayValue(365, "1");
        man.add(man1);
        man.add(man7);
        man.add(man30);
        man.add(man365);
        map.put("man",man);
        //女性的注册趋势
        Integer woman1 = findCountForInDayValue(1, "2");
        Integer woman7 = findCountForInDayValue(7, "2");
        Integer woman30 = findCountForInDayValue(30, "2");
        Integer woman365 = findCountForInDayValue(365, "2");
        woman.add(woman1);
        woman.add(woman7);
        woman.add(woman30);
        woman.add(woman365);
        map.put("woman",woman);
        return map;
    }

    /**
     * 返回用户的注册分布信息
     * @return
     */
    @Override
    public List<MapVo> findCountUserLocation() {
        return cfUserMapper.selectCountByLocation();
    }

    /**
     * 根据性别和日期查找近dayValue天内注册的用户
     * @param dayValue
     * @param sex
     * @return
     */
    @Override
    public Integer findCountForInDayValue(Integer dayValue, String sex) {
        CfUserExample example = new CfUserExample();
        CfUserExample.Criteria criteria = example.createCriteria();
        criteria.andSexEqualTo(sex);
        example.setDayValue(dayValue);
        Integer count = cfUserMapper.countForInDayValue(example);
        return count;
    }

    @Override
    public CfUser findOneByMobilePhone(String mobilePhone) {
        CfUser cfUser = null;
        CfUserExample example = new CfUserExample();
        CfUserExample.Criteria criteria = example.createCriteria();
        criteria.andMobilePhoneEqualTo(mobilePhone);
        List<CfUser> cfUsers = cfUserMapper.selectByExample(example);
        if(cfUsers.size()!=0) {
            cfUser = cfUsers.get(0);//查找到该手机号
            return cfUser;
        }else {
            return null;
        }
    }

    @UserRegister("修改用户状态")
    @LogAnnotation("修改用户状态")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void alter(CfUser cfUser) {
        cfUserMapper.updateByPrimaryKeySelective(cfUser);
    }

    @UserRegister("删除用户")
    @LogAnnotation("删除用户")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void drop(CfUser cfUser) {
        cfUserMapper.deleteByPrimaryKey(cfUser.getUserId());
    }

    @UserRegister("添加用户")
    @LogAnnotation("添加用户")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(CfUser cfUser) {
        cfUserMapper.insertSelective(cfUser);
    }
}
