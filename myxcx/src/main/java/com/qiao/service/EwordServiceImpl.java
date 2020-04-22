package com.qiao.service;

import com.qiao.dao.EwordsDao;
import com.qiao.entity.Ewords;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EwordServiceImpl implements EwordsService {
    @Autowired
    private EwordsDao ewordsDao;


    @Override
    public List<Ewords> showAll() {
        return ewordsDao.selectAll();
    }

    @Override
    public Map showAllEwordsByPage(Integer page, Integer rows) {
        HashMap hashMap=new HashMap();
        //分页查询
        List<Ewords> ewords = ewordsDao.selectByRowBounds(new Ewords(), new RowBounds((page - 1) * rows, rows));
        int records=ewordsDao.selectCount(new Ewords());  //计算总数
        int total=records % rows == 0 ? records / rows :records /rows +1; //三木运算
        hashMap.put("rows",ewords);
        hashMap.put("page",page);
        hashMap.put("records",records);
        hashMap.put("total",total);
        return hashMap;
    }

    //添加词汇
    @Override
    //加入事务处理
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map addEwords(Ewords ewords) {
        HashMap hashMap=new HashMap();
        try{
            ewordsDao.insert(ewords);//添加词汇
            hashMap.put("status","true");
        }catch (Exception e){
            hashMap.put("status","false");
            System.out.println(e.getLocalizedMessage());//打印错误信息
           // e.printStackTrace();
        }finally {
            return hashMap;
        }
    }


    @Override
    //模糊查询
    public List<Ewords> queryLike(String eword, String flag) {

        List<Ewords> ewordsList = ewordsDao.queryLikeEwords(eword, flag);
        if (ewordsList.isEmpty()){
            return null;
        }
        ewordsList.add(new Ewords("结果不精确？","请键入更多关键字！"));
        return ewordsList;
    }
    //分页查询



}
