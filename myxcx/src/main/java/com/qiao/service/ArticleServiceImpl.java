package com.qiao.service;

import com.qiao.dao.ArticleDao;
import com.qiao.entity.Article;
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
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;


    @Override
    public Map showAllArticleByPage(Integer page, Integer rows) {
        HashMap hashMap=new HashMap();
        //分页查询
        List<Article> articles = articleDao.selectByRowBounds(new Article(), new RowBounds((page - 1) * rows, rows));
        int records=articleDao.selectCount(new Article());  //计算总数
        int total=records % rows == 0 ? records / rows :records /rows +1; //三木运算
        hashMap.put("rows",articles);
        hashMap.put("page",page);
        hashMap.put("records",records);
        hashMap.put("total",total);
        return hashMap;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map addSentence(Article article) {
        HashMap hashMap = new HashMap();
        try {
            article.setId(GetUUIDUtils.getUUID32()).setCreatedate(new Date());
           // System.out.println(article);
            articleDao.insert(article);//添加文章
            hashMap.put("status", "200");
        } catch (Exception e) {
            hashMap.put("status", "400");
            System.out.println(e.getLocalizedMessage());//打印错误信息
            // e.printStackTrace();
        } finally {
            return hashMap;
        }
    }
}
