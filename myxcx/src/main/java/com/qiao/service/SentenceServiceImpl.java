package com.qiao.service;

import com.qiao.dao.SentenceDao;
import com.qiao.entity.Sentence;
import com.qiao.util.GetUUIDUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SentenceServiceImpl implements  SentenceService {
    //短句相关
    @Autowired
    private SentenceDao sentenceDao;


    @Override
    public Map showAllSentenceByPage(Integer page, Integer rows) {
        HashMap hashMap=new HashMap();
        //分页查询
        List<Sentence> ewords = sentenceDao.selectByRowBounds(new Sentence(), new RowBounds((page - 1) * rows, rows));
        int records=sentenceDao.selectCount(new Sentence());  //计算总数
        int total=records % rows == 0 ? records / rows :records /rows +1; //三木运算
        hashMap.put("rows",ewords);
        hashMap.put("page",page);
        hashMap.put("records",records);
        hashMap.put("total",total);
        return hashMap;
    }

    //添加句子
    @Override
    //加入事务处理
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map addSentence(Sentence sentence) {
        HashMap hashMap = new HashMap();
        try {
            sentence.setId(GetUUIDUtils.getUUID32()).setCreatedata(new Date());
            sentenceDao.insert(sentence);//添加词汇
            hashMap.put("status", "true");
        } catch (Exception e) {
            hashMap.put("status", "false");
            System.out.println(e.getLocalizedMessage());//打印错误信息
            // e.printStackTrace();
        } finally {
            return hashMap;
        }
    }


}
