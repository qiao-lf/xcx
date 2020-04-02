package henu.xmh.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Course implements Serializable {
    private String courseId;

    private String courseName;

    private String userId;

    private String courseStyle;

    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

}