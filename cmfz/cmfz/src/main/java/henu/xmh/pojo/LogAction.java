package henu.xmh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class LogAction implements Serializable {
    private String admin;//账户名

    private Date date;//时间

    private String clazzName;//类名

    private String method;//方法名

    private String action;//事件信息
}
