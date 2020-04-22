package com.qiao.service;

import com.qiao.entity.Article;

import java.util.Map;

public interface ArticleService {


    public Map showAllArticleByPage(Integer page, Integer rows);

    public Map addSentence(Article article);
}
