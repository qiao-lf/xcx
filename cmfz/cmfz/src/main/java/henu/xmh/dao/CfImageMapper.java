package henu.xmh.dao;

import henu.xmh.pojo.CfImage;
import henu.xmh.pojo.CfImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;

public interface CfImageMapper extends DeleteByIdListMapper<CfImage,String> {
    int countByExample(CfImageExample example);

    int deleteByExample(CfImageExample example);

    int deleteByPrimaryKey(String imageId);

    int insert(CfImage record);

    int insertSelective(CfImage record);

    List<CfImage> selectByExample(CfImageExample example);

    CfImage selectByPrimaryKey(String imageId);

    int updateByExampleSelective(@Param("record") CfImage record, @Param("example") CfImageExample example);

    int updateByExample(@Param("record") CfImage record, @Param("example") CfImageExample example);

    int updateByPrimaryKeySelective(CfImage record);

    int updateByPrimaryKey(CfImage record);
}