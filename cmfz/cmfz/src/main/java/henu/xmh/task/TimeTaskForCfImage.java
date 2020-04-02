package henu.xmh.task;

import com.alibaba.excel.EasyExcel;
import henu.xmh.pojo.CfImage;
import henu.xmh.service.CfImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XiaMH
 * 开启多线程的定时任务
 */
@Component
@Async
public class TimeTaskForCfImage {
    @Autowired
    private CfImageService cfImageService;

    /**
     * 每周一上午 10:15生成轮播图信息的表格
     */
    @Scheduled(cron = "0 15 10 ? * MON")
    public void toExcelForCfImage(){
        System.out.println("cron"+new Date());
        String fileName = "excelForCfImage"+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        System.out.println(fileName);
        EasyExcel.write("D://"+fileName+".xls", CfImage.class).sheet().doWrite(cfImageService.findAll());
    }

}
