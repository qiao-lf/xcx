package com.qiao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Admin implements Serializable {
    //管理员实体嘞
    @Id
    private String id;
    //登陆凭证
    private String username;
    private String password;
    //管理员昵称
    private String nickname;
    @Transient
    private List<Roles> roles;

}
