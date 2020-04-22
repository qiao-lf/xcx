package com.qiao.service;

import com.qiao.entity.Ewords;

import java.util.List;
import java.util.Map;

public interface EwordsService {

    public List<Ewords> showAll();

    public Map showAllEwordsByPage(Integer page,Integer rows);

    public Map addEwords(Ewords ewords);

    public List<Ewords> queryLike(String eword,String flag);

}
