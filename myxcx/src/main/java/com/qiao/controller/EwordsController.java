package com.qiao.controller;

import com.qiao.entity.Ewords;
import com.qiao.service.EwordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("ed")
public class EwordsController {

    @Autowired
    private EwordsService ewordsService;//service接口



    //分页展示全部
    @ResponseBody
    @RequestMapping("showAllEwordsByPage")
    public Map showAllByPage(Integer page, Integer rows ){
        Map map = ewordsService.showAllEwordsByPage(page, rows);
        return map;
    }

    //后台修改操作

    @ResponseBody
    @RequestMapping("editEwords")
    public Map edit(String oper, Ewords ewords){
        Map hashMap=new HashMap();
        if (oper.equals("add")){
            Map map = ewordsService.addEwords(ewords);
            return map;
        }
        hashMap.put("message","上传失败");
        return  hashMap;
    }



}
