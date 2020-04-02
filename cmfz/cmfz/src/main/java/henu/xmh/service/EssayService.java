package henu.xmh.service;

import henu.xmh.pojo.Essay;

import java.util.List;
import java.util.Map;

public interface EssayService {
    Map<String ,Object> findAllEssayForPage(Integer rows, Integer page);

    void add(Essay essay);

    void drop(Essay essay);

    void alter(Essay essay);

    Map<String,Object> editForJq(String oper,Essay essay,String[] id);

    Map<String,Object> findForJqSearchForPage(String searchString,String searchOper,String searchField,Integer rows,Integer page);

    Map<String,Object> findByElasticSearchForPage(String searchString,Integer page,Integer size);
}

