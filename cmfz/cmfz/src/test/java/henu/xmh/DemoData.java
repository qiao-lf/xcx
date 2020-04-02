package henu.xmh;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData implements Serializable {

    @ExcelProperty("字符串标题")
    private String string;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "日期格式标题",index = 4)
    private Date date;

    @NumberFormat("#.##")
    @ExcelProperty("数字标题")
    private Double doubleData;

    /**
     * 忽略此字段
     */
    @ExcelIgnore
    private String sttr;
}
