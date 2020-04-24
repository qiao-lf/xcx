package com.qiao.controller;

import com.qiao.entity.Feedback;
import com.qiao.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("fb")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    @ResponseBody
    @RequestMapping("showAllFeedbackByPage")
    public Map showAllFeedbackByPage(Integer page, Integer rows){
        return  feedbackService.showAllFeedbackByPage(page,rows);
    }


    @ResponseBody
    @RequestMapping("updateStatus")
    public Map updateStatus(String id){
        //System.out.println(id);
        Feedback feedback = feedbackService.findFeedback(id);
        HashMap hashMap = new HashMap();
        if (feedback.getId().isEmpty()){//对象ID为主键  必定不为空 因此判断主键就可以得知
            hashMap.put("status", "-200");
            hashMap.put("message", "数据传输失败了 ");
            return hashMap;
        }
        Map map = feedbackService.freezeFeedback(feedback);
        return map;
    }
}
