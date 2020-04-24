package com.qiao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Article {
    @Id
    private String id;
    private String title;
    private String ctitle;
    private String earticle;
    private String carticle;

    @JSONField(format = "yyyy-MM-dd")  //FASTJSON 日期转换
    @DateTimeFormat(pattern = "yyyy-MM-dd")  //数据库日期格式转换
    private Date createdate;
    //private String picture;
}
