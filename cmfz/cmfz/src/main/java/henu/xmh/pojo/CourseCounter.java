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
public class CourseCounter implements Serializable {
    private String counterId;//计数器id

    private String counterName;//计数器名

    private Integer counterNum;//计数器数量

    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;//计数器创建时间

    private String courseId;//所属课程id

    private String userId;//所属用户ID

}