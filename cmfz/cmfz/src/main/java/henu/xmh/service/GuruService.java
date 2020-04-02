package henu.xmh.service;

import henu.xmh.pojo.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {
    List<Guru> findAll();

    Map<String,Object> findAllForPage(Integer page,Integer rows);

    Map<String,Object> alterForJq(String oper,Guru guru,String[] id);

    void add(Guru guru);

    void drop(Guru guru);

    void alter(Guru guru);

    Map<String,Object> findAllForJqSearch(String searchField,String searchString
            ,String searchOper,Integer page,Integer rows);
}
