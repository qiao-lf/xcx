package henu.xmh.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ContentRowHeight(10)
@HeadRowHeight(20)
@ColumnWidth(25)
public class CfImage implements Serializable {
    @Id
    private String imageId;

    @ExcelProperty("原始名")
    private String orgName;

    @ExcelProperty("图片实际名")
    private String newName;

    @ExcelProperty("图片状态")
    private String imageStatus;

    @ExcelProperty("上传日期")
    private String imageDir;

    @ExcelProperty("图片描述")
    private String imageSummary;

    @ExcelProperty("超链接")
    private String imageHref;

    private String id;

}