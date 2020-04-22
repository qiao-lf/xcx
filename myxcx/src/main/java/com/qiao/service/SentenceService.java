package com.qiao.service;

import com.qiao.entity.Sentence;

import java.util.Map;

public interface SentenceService {

    public Map showAllSentenceByPage(Integer page, Integer rows);

    public Map addSentence(Sentence sentence);


}
