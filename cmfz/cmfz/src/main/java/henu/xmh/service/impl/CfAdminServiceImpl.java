package henu.xmh.service.impl;

import henu.xmh.annotation.LogAnnotation;
import henu.xmh.dao.CfAdminDao;
import henu.xmh.dao.CfAdminMapper;
import henu.xmh.pojo.CfAdmin;
import henu.xmh.pojo.CfAdminExample;
import henu.xmh.service.CfAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CfAdminServiceImpl implements CfAdminService {

    @Autowired
    private CfAdminDao cfAdminDao;


    @Override
    public void login(String username, String password) {
        CfAdmin cfAdmin = cfAdminDao.selectByPrimaryKey(username);
        if(cfAdmin==null)throw new RuntimeException("账户名输入错误！");
        if(!cfAdmin.getPassword().equals(password)) throw  new RuntimeException("密码输入错误！");
    }


    @Autowired
    private CfAdminMapper cfAdminMapper;

    /**
     * jqgrid的管理员信息的分页查询方法
     * @param rows
     * @param page
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> findCfAdminRolesAndResourcesForPage(Integer rows, Integer page) {
        Map<String, Object> result = new HashMap<>();
        CfAdminExample example = new CfAdminExample();
        example.setBeginValue((page-1)*rows);
        example.setPageSize(rows);
        //该页显示信息
        List<CfAdmin> cfAdmins = cfAdminMapper.selectCfAdminRolesAndResourcesForPage(example);
        //记录数
        Integer records = cfAdminMapper.countByExample(example);
        //页数
        Integer total = (records%rows==0)?(records/rows):(records/rows+1);

        result.put("rows",cfAdmins);
        result.put("records",records);
        result.put("total",total);
        result.put("page",page);
        return result;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @LogAnnotation("修改管理员权限")
    @Override
    public Map<String, Object> alterCfAdminForJq(String oper, CfAdmin cfAdmin) {
        return null;
    }
}
