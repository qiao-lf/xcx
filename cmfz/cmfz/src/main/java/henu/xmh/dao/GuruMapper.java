package henu.xmh.dao;

import henu.xmh.pojo.Guru;
import henu.xmh.pojo.GuruExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GuruMapper {
    int countByExample(GuruExample example);

    int deleteByExample(GuruExample example);

    int deleteByPrimaryKey(String guruId);

    int insert(Guru record);

    int insertSelective(Guru record);

    List<Guru> selectByExample(GuruExample example);

    Guru selectByPrimaryKey(String guruId);

    int updateByExampleSelective(@Param("record") Guru record, @Param("example") GuruExample example);

    int updateByExample(@Param("record") Guru record, @Param("example") GuruExample example);

    int updateByPrimaryKeySelective(Guru record);

    int updateByPrimaryKey(Guru record);
}