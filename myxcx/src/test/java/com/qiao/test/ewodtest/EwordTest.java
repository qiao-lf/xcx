package com.qiao.test.ewodtest;


import com.qiao.MyxcxApplication;
import com.qiao.dao.EwordsDao;
import com.qiao.entity.Ewords;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MyxcxApplication.class)
@RunWith(SpringRunner.class)
public class EwordTest {

    @Autowired
    private EwordsDao ewordsDao;

    @Test
    public void t1(){
    //测试模糊查询
  /**
        List<Ewords> aba = ewordsDao.queryLikeEwords("a","like");
        aba.forEach(ewor ->{
            System.out.println(ewor);
        });
*/
        System.out.println("*******************************");
     //测试精确查询
       List<Ewords> aba=ewordsDao.queryLikeEwords("a","query");

       if(!aba.isEmpty()){
           System.out.println("-------");
           aba.forEach(ew->{

               System.out.println("*******"+ew+"*******");
           });
       }


    }
}
