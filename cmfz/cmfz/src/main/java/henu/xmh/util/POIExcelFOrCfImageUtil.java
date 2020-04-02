package henu.xmh.util;

import henu.xmh.dao.CfImageMapper;
import henu.xmh.dao.CfUserMapper;
import henu.xmh.pojo.CfImage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class POIExcelFOrCfImageUtil {

    public static void toDownLoad(List<CfImage> cfImages, OutputStream outputStream){
        //创建Excel表格对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("图片信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        String[] title = {"图片ID","图片原始名","图片实际名","图片状态","目录","图片描述","超链接"};
        //设置单元格
        HSSFCell cell = null;
        for(int i = 0 ;i<title.length;i++){
            cell = row.createCell(i);//单元格下标
            cell.setCellValue(title[i]);//设置单元格内容
            //cell.setCellStyle(cellStyle);设置单元格的字体
        }
        //处理数据行
        for (int i = 0; i < cfImages.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            CfImage cfImage = cfImages.get(i);
            //每行对应的数据
            row1.createCell(0).setCellValue(cfImage.getImageId());//图片Id
            row1.createCell(1).setCellValue(cfImage.getOrgName());//原始名
            row1.createCell(2).setCellValue(cfImage.getNewName());//实际名
            row1.createCell(3).setCellValue(cfImage.getImageStatus());//图片状态
            row1.createCell(4).setCellValue(cfImage.getImageDir());//目录
            row1.createCell(5).setCellValue(cfImage.getImageSummary());//图片描述
            row1.createCell(6).setCellValue(cfImage.getImageHref());//超链接

        }
        try {
            workbook.write(outputStream);//打到网络输出流
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
