package henu.xmh.dao;

import henu.xmh.pojo.CfAlbum;
import henu.xmh.pojo.CfAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;

public interface CfAlbumMapper extends DeleteByIdListMapper<CfAlbum,String> {


    int countByExample(CfAlbumExample example);

    int deleteByExample(CfAlbumExample example);

    int deleteByPrimaryKey(String albumId);

    int insert(CfAlbum record);

    int insertSelective(CfAlbum record);

    List<CfAlbum> selectByExampleWithBLOBs(CfAlbumExample example);

    List<CfAlbum> selectByExample(CfAlbumExample example);

    CfAlbum selectByPrimaryKey(String albumId);

    int updateByExampleSelective(@Param("record") CfAlbum record, @Param("example") CfAlbumExample example);

    int updateByExampleWithBLOBs(@Param("record") CfAlbum record, @Param("example") CfAlbumExample example);

    int updateByExample(@Param("record") CfAlbum record, @Param("example") CfAlbumExample example);

    int updateByPrimaryKeySelective(CfAlbum record);

    int updateByPrimaryKeyWithBLOBs(CfAlbum record);

    int updateByPrimaryKey(CfAlbum record);
}