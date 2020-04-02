package henu.xmh.dao;

import henu.xmh.pojo.CfAdmin;
import henu.xmh.pojo.CfAdminExample;
import java.util.List;

import henu.xmh.pojo.CfAlbum;
import org.apache.ibatis.annotations.Param;

public interface CfAdminMapper {

    //自定义方法1.查询管理员的角色和权限(分页查询||条件查询)(5表联查)
    List<CfAdmin> selectCfAdminRolesAndResourcesForPage(CfAdminExample cfAdminExample);

    //自定义方法2.查询管理员的所有角色信息，以及用户角色的权限信息（根据username查询）(5表联查)
    CfAdmin selectCfAdminRolesAndResourcesByUsername(String username);

    int countByExample(CfAdminExample example);

    int deleteByExample(CfAdminExample example);

    int deleteByPrimaryKey(String username);

    int insert(CfAdmin record);

    int insertSelective(CfAdmin record);

    List<CfAdmin> selectByExample(CfAdminExample example);

    CfAdmin selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") CfAdmin record, @Param("example") CfAdminExample example);

    int updateByExample(@Param("record") CfAdmin record, @Param("example") CfAdminExample example);

    int updateByPrimaryKeySelective(CfAdmin record);

    int updateByPrimaryKey(CfAdmin record);
}