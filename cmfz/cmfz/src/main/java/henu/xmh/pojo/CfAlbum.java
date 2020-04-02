package henu.xmh.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CfAlbum implements Serializable {
    @Id
    private String albumId;//专辑id

    private String albumName;//专辑名

    private String albumCover;//专辑封面

    private String author;//作者

    private String announcer;//播音

    private Double score;//评分星级

    @JSONField(format = "yyyy-MM-dd")
    private Date alibumDate;//发布日期

    private Integer albumNum;//章节数

    private String summary;//简介

}