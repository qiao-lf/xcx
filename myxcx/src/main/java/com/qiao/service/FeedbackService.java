package com.qiao.service;

import com.qiao.entity.Feedback;

import java.util.Map;

public interface FeedbackService {

    /**
     * 根据ID查询反馈信息
     * @param id
     * @return
     */
    public Feedback findFeedback(String id);



    /**
     * 后台分页查询
     * @param page   页数
     * @param rows   每页数据量
     * @return
     */
    public Map showAllFeedbackByPage(Integer page, Integer rows );


    /**
     * 管理员操作   已处理  未处理
     * @param feedback   反馈信息
     * @return
     */
    public Map freezeFeedback(Feedback feedback);


}
