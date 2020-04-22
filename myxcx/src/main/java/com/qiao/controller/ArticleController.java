package com.qiao.controller;

import com.qiao.entity.Article;
import com.qiao.entity.Ewords;
import com.qiao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("ar")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("showAllArticleByPage")
    public Map showAllArticleByPage(Integer page, Integer rows){
        Map map = articleService.showAllArticleByPage(page, rows);
        return map;
    }

    @ResponseBody
    @RequestMapping("addArticle")
    public Map addArticle(Article article){
        Map map = articleService.addSentence(article);

        return  map;
    }
}
