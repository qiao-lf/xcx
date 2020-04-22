package com.qiao.controller;

import com.qiao.entity.Sentence;
import com.qiao.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("se")
public class SentenceController {
    @Autowired
    private SentenceService sentenceService;

    @ResponseBody
    @RequestMapping("showAllSentenceByPage")
    public Map showAllSentenceByPage(Integer page, Integer rows){
        Map map = sentenceService.showAllSentenceByPage(page, rows);
        return map;
    }



    @ResponseBody
    @RequestMapping("editSentence")
    public Map edit(String oper, Sentence sentence) {
        Map hashMap=new HashMap();
        if (oper.equals("add")){
            Map map = sentenceService.addSentence(sentence);
            return map;
        }
        hashMap.put("message","上传失败");
        //修改 edit
        //删除 del
        return  hashMap;
    }

}
