package henu.xmh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Chapter implements Serializable {
    @Id
    private String chapterId;

    private String voiceurl;

    private String albumId;

    private String chapterName;

    private String size;

    private String timeLong;

    private String id;
}