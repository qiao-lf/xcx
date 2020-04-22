package com.qiao.dao;

import com.qiao.entity.Ewords;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EwordsDao extends Mapper<Ewords> {

    //针对模糊查询的查询
    List<Ewords> queryLikeEwords(
            @Param(value = "ew") String ew,
            @Param(value = "flag") String flag);


}
