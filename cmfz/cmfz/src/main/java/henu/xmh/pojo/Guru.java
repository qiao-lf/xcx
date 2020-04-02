package henu.xmh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Guru {
    private String guruId;//上师id

    private String guruName;//上师姓名

    private String guruImage;//上师头像

    private String guruNickname;//上师法号

    private String id;

}