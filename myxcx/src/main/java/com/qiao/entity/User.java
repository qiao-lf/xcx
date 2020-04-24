package com.qiao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
   @Id
    private String id;
    private String phone;
    private String password;
    private String nickname;
    private String name;
    private String status;//状态
    @JSONField(format = "yyyy-MM-dd")  //FASTJSON 日期转换
    @DateTimeFormat(pattern = "yyyy-MM-dd")  //数据库日期格式转换
    private Date createdata;
}
