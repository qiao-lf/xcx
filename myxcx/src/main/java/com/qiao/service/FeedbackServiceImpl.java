package com.qiao.service;

import com.qiao.dao.FeedbackDao;
import com.qiao.entity.Feedback;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;


    @Override
    public Feedback findFeedback(String id) {
        return feedbackDao.selectOne(new Feedback().setId(id));
    }

    @Override
    public Map showAllFeedbackByPage(Integer page, Integer rows) {
        HashMap hashMap=new HashMap();
        //分页查询
        List<Feedback> feedbacks = feedbackDao.selectByRowBounds(new Feedback(), new RowBounds((page - 1) * rows, rows));
        int records=feedbackDao.selectCount(new Feedback());  //计算总数
        int total=records % rows == 0 ? records / rows :records /rows +1; //三木运算
        hashMap.put("rows",feedbacks);
        hashMap.put("page",page);
        hashMap.put("records",records);
        hashMap.put("total",total);
        return hashMap;
    }

    @Override
    public Map freezeFeedback(Feedback feedback) {
        HashMap hashMap = new HashMap();
        if(feedback.getStatus().equals("未处理")){
            try{
                feedback.setStatus("已处理");
                //System.out.println("------------------");
                //System.out.println(feedback);
                feedbackDao.updateByPrimaryKeySelective(feedback);
                hashMap.put("status", "200");
                hashMap.put("message", "处理成功 ");
            }catch(Exception e){
                hashMap.put("status","-200");
                hashMap.put("message","处理失败");
            }finally{
                return hashMap;
            }
        }else {
            hashMap.put("status","200");
            hashMap.put("message","已处理过反馈，该条反馈无法处理");
            return hashMap;
        }
    }
}
