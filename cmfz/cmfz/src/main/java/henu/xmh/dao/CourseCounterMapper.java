package henu.xmh.dao;

import henu.xmh.pojo.CourseCounter;
import henu.xmh.pojo.CourseCounterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseCounterMapper {
    int countByExample(CourseCounterExample example);

    int deleteByExample(CourseCounterExample example);

    int deleteByPrimaryKey(String counterId);

    int insert(CourseCounter record);

    int insertSelective(CourseCounter record);

    List<CourseCounter> selectByExample(CourseCounterExample example);

    CourseCounter selectByPrimaryKey(String counterId);

    int updateByExampleSelective(@Param("record") CourseCounter record, @Param("example") CourseCounterExample example);

    int updateByExample(@Param("record") CourseCounter record, @Param("example") CourseCounterExample example);

    int updateByPrimaryKeySelective(CourseCounter record);

    int updateByPrimaryKey(CourseCounter record);
}