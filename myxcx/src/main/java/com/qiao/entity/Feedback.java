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

/**
 * 反馈表
 * id
 * 用户id
 * 信息
 * 状态
 *创建时间
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Feedback implements Serializable {
    @Id
    private String id;
    private String userid;
    private String message;
    private String status;
    @JSONField(format = "yyyy-MM-dd")  //FASTJSON 日期转换
    @DateTimeFormat(pattern = "yyyy-MM-dd")  //数据库日期格式转换
    private Date createtime;
}
