package henu.xmh.dao;

import henu.xmh.pojo.CfUser;
import henu.xmh.pojo.CfUserExample;
import java.util.List;

import henu.xmh.pojo.MapVo;
import org.apache.ibatis.annotations.Param;

public interface CfUserMapper {
    List<MapVo> selectCountByLocation();

    int countByExample(CfUserExample example);

    int deleteByExample(CfUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(CfUser record);

    int insertSelective(CfUser record);

    Integer countForInDayValue(CfUserExample example);

    List<CfUser> selectByExample(CfUserExample example);

    CfUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") CfUser record, @Param("example") CfUserExample example);

    int updateByExample(@Param("record") CfUser record, @Param("example") CfUserExample example);

    int updateByPrimaryKeySelective(CfUser record);

    int updateByPrimaryKey(CfUser record);
}