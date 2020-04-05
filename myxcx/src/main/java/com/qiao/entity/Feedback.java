package com.qiao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    private String id;
    private String userid;
    private String message;
    private String status;
    private Date createtime;
}
