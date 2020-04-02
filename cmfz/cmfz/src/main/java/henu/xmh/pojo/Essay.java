package henu.xmh.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Document(indexName = "cmfz",type = "essay")
public class Essay implements Serializable {

    @Id
    private String essayId;

    @Transient
    private String guruId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Field(type = FieldType.Date)
    private Date essayTime;

    @Transient
    private String essayCover;//状态

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String essayTittle;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String author;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String essayContent;

    @Transient
    private String id;

    @Transient
    private Guru guru;

}