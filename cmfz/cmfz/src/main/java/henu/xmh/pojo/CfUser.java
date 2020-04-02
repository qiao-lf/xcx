package henu.xmh.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CfUser implements Serializable {
    private String userId;//用户id

    private String username;//用户名

    private String password;//密码

    private String cfUseranme;//法号

    private String sex;//性别

    private String headImage;//头像

    private String idiograph;//个性签名

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date loginTime;//上次登录时间

    private String mobilePhone;//手机号

    private String userStatus;//用户状态

    private String salut;//盐

    private String location;//所在地

    private String register;//注册时间

}