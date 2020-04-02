package henu.xmh;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import henu.xmh.dao.*;
import henu.xmh.pojo.*;
import henu.xmh.service.*;
import henu.xmh.util.UserRandomUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {

    @Autowired
    private CfAdminDao cfAdminDao;

    @Test
    public void contextLoads() {
        List<CfAdmin> cfAdmins = cfAdminDao.selectAll();
        cfAdmins.forEach(admin-> System.out.println(admin));
    }

    @Test
    public void test1(){
        CfAdmin cfAdmin = new CfAdmin();
        cfAdmin.setUsername("xiaohei1").setPassword("123");
        int i = cfAdminDao.insertSelective(cfAdmin);
        System.out.println(i);
        CfAdmin xmh = cfAdminDao.selectByPrimaryKey("xmh");
        System.out.println(xmh);
    }

    @Autowired
    private CfImageService cfImageService;

    @Test
    public void testCfIjmage(){
        List<CfImage> cfImages = cfImageService.finaAllCfImageForPage(0, 2);
        cfImages.forEach(im-> System.out.println(im));
    }

    @Autowired
    private CfImageMapper cfImageMapper;
    @Test
    public void testDelCfIjmageByIdList(){
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        int i = cfImageMapper.deleteByIdList(ids);
        System.out.println(i);
    }

    @Test
    public void testSpilt(){
        String[] split = "http://192.168.0.17:8989/cmfz/upload/audio/1574905236048_李志 - 卡夫卡.mp3".split("/");
        System.out.println(split[6]);
    }

    @Autowired
    private LogActionService logActionService;

    @Autowired
    private LogActionMapper logActionMapper;
    @Test
    public void testLogActionService(){
        List<LogAction> logActions = logActionMapper.selectByRowBounds(new LogAction(), new RowBounds(0, 4));
        logActions.forEach(l-> System.out.println(l));
    }

    @Autowired
    private EssayMapper essayMapper;
    @Test
    public void testEssAyMapper(){
        EssayExample example = new EssayExample();
        example.setBeginValue(0);
        example.setPageSize(2);
        example.createCriteria().andGuruIdEqualTo("1");
        List<Essay> essays = essayMapper.selectByExampleLeftJoinGuru(example);
        essays.forEach(e-> System.out.println(e));
    }

    @Test
    public void testPoiForHSSF(){
        HSSFWorkbook workbook = new HSSFWorkbook();
    }

    @Test
    public void testEssayExcel(){//基础写法一

        EasyExcel.write("D://testEasyExcel1.xls",DemoData.class).sheet().doWrite(data());
    }

    private List<DemoData> data(){
        DemoData xmh = new DemoData("xmh", new Date(), 20.3D, "111");
        DemoData xmh1 = new DemoData("xmh1", new Date(), 20.3D, "111");
        DemoData xmh2 = new DemoData("xmh2", new Date(), 20.3D, "111");
        DemoData xmh3 = new DemoData("xmh3", new Date(), 20.3D, "111");
        List<DemoData> demoData = Arrays.asList(xmh, xmh1, xmh2, xmh3);
        return demoData;
    }

    @Test
    public void testFrsa(){
        DemoDataListener demoDataListener = new DemoDataListener();
        EasyExcel.read("D://testEasyExcel1.xls",DemoData.class,demoDataListener).sheet().doRead();
        List<DemoData> lists = demoDataListener.getLists();
        System.out.println("========================================");
        lists.forEach(l-> System.out.println(l));
    }
    @Test
    public void testBigData(){
        //指定文件创建
        ExcelWriter excelWriter = EasyExcel.write("D://testEasyExcelBig222.xls", ConverterData.class).build();
        //生成30页,每页60000条数据
        for(int i = 0;i<30;i++){
            //每次创建一页,
            WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
            //一页写30次,每次写入2000条
            for(int j = 0;j<30;j++){
                excelWriter.write(data2(),writeSheet);
            }
        }
        excelWriter.finish();//关流
    }

    private List<ConverterData> data2(){
        List<ConverterData> converterData = new ArrayList<>();

        for(int i = 0;i<400;i++){
            ConverterData xmh1 = new ConverterData("xmh1", new Date(), 0.77D);
            ConverterData xmh2 = new ConverterData("xmh2", new Date(), 0.77D);
            ConverterData xmh3 = new ConverterData("xmh3", new Date(), 0.77D);
            ConverterData xmh4 = new ConverterData("xmh4", new Date(), 0.77D);
            ConverterData xmh5 = new ConverterData("xmh5", new Date(), 0.77D);
            converterData.add(xmh1);
            converterData.add(xmh2);
            converterData.add(xmh3);
            converterData.add(xmh4);
            converterData.add(xmh5);
        }
        return converterData;

    }

    @Test
    public void testEasyExcel2(){
        ConverterData xmh1 = new ConverterData("xmh1", new Date(), 0.77D);
        ConverterData xmh2 = new ConverterData("xmh2", new Date(), 0.77D);
        ConverterData xmh3 = new ConverterData("xmh3", new Date(), 0.77D);
        ConverterData xmh4 = new ConverterData("xmh4", new Date(), 0.77D);
        ConverterData xmh5 = new ConverterData("xmh5", new Date(), 0.77D);
        List<ConverterData> converterData = Arrays.asList(xmh1, xmh2, xmh3, xmh4, xmh5);


        EasyExcel.write("D://testConverterDataEasyExcel1.xls",ConverterData.class).sheet().doWrite(converterData);
    }

    @Autowired
    private CfUserService cfUserService;
    @Test
    public void testInsertData(){
        for (int i =0;i<100;i++){
            CfUser cfUser = new CfUser().setUserId(UUID.randomUUID().toString().replace("-", ""))
                    .setUserStatus("1").setCfUseranme(UserRandomUtil.getName()).setHeadImage("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4196133035,4167956900&fm=26&gp=0.jpg")
                    .setIdiograph("这是一段签名").setLocation("北京").setLoginTime(new Date()).setMobilePhone("17611208882")
                    .setPassword("123").setRegister(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).setUsername("xmh"+i);
            int random = (int) (Math.random()*100);
            if(random%2==0) cfUser.setSex("1");
            else cfUser.setSex("2");
           cfUser.setLocation(UserRandomUtil.getProCity());
            cfUserService.add(cfUser);
        }
    }
    @Test
    public void testRodom(){
        int random = (int) (Math.random()*100);
        System.out.println(random);
    }
   /* @Test
    public void testPOI(){
        HSSFWorkbook sheets = new HSSFWorkbook();//创建一个excel文档
        HSSFSheet sheet = sheets.createSheet("轮播图信息表");//创建一个工作簿
        HSSFRow row = sheet.createRow(0);//创建一行
        HSSFCell cell = row.createCell(0);//创建一个单元格
        cell.setCellValue("这是第一行的一个单元格");

        try {//导出单元格
            sheets.write(new FileOutputStream(new File("D://testPOI.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                sheets.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Test
    public void testPOI2(){
        List<CfImage> cfImages = cfImageService.finaAllCfImageForPage(1, 5);
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
            workbook.write(new FileOutputStream(new File("D://testImag.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }*/
   @Autowired
   private CfUserMapper cfUserMapper;
   @Test
   public void testMapperForDayValue(){
       CfUserExample example = new CfUserExample();
       CfUserExample.Criteria criteria = example.createCriteria();
       criteria.andSexEqualTo("1");
       example.setDayValue(1);
       Integer count = cfUserMapper.countForInDayValue(example);
       System.out.println(count+"");

   }

   @Test
   public void testCfUserServiceForCount(){
       Map<String, Object> registerDevelopment = cfUserService.findRegisterDevelopment();
       System.out.println(registerDevelopment);
   }

   @Test
   public void testMapVo(){
       List<MapVo> mapVos = cfUserMapper.selectCountByLocation();
       mapVos.forEach(m-> System.out.println(m));
   }

    @Autowired
    private RedisTemplate redisTemplate;

   @Test
    public void testRedis(){
       SetOperations setOperations = redisTemplate.opsForSet();
       List<String> list = new ArrayList<>();
       list.add("1");
       list.add("2");
       list.add("3");
       setOperations.add("1111",list);
   }

   @Test
   public void testAddRedis(){
       SetOperations setOperations = redisTemplate.opsForSet();
       List<String> list = new ArrayList<>();
       list.add("1");
       list.add("2");
       list.add("3");
       Long add = setOperations.add("005bf368faa14452bd47f506867a6cca", list);
       System.out.println(add);
   }

    @Test
    public void testAddRedis1(){
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("005bf368faa14452bd47f506867a6cca", "1");
        setOperations.add("005bf368faa14452bd47f506867a6cca", "3");

        setOperations.add("1","005bf368faa14452bd47f506867a6cca");
        setOperations.add("3","005bf368faa14452bd47f506867a6cca");
    }

    @Test
    public void testMail(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FmUv6HdgYsRqRSUnXyt", "sDFuPtJsw957CQGjKZTS9VNvFRh6gI");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "17611208892");
        request.putQueryParameter("SignName", "FGJISA");
        request.putQueryParameter("TemplateCode", "SMS_179605224");
        request.putQueryParameter("TemplateParam", "{\"code\":\"sddd\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testString(){
        String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        System.out.println(substring);
    }

    @Autowired
    private CmfzAppService cmfzAppService;
    @Test
    public void testString1(){
        String s = cmfzAppService.sendMail("17611208892");
        System.out.println(s);
    }


    @Autowired
    private CfUserAboutGuruService cfUserAboutGuruService;

    @Test
    public void testAbout(){
        cfUserAboutGuruService.aboutGuru("0557c333f3474edd858bbe1a35358c1b","3");
    }

    @Test
    public void testAbout1(){
        List<CfUser> cfUsers = cfUserAboutGuruService.recommendCfUser("005bf368faa14452bd47f506867a6cca");
        cfUsers.forEach(u-> System.out.println(u));
    }
    @Test
    public void testAbout2(){
        SetOperations setOperations = redisTemplate.opsForSet();
        Set members = setOperations.members("005bf368faa14452bd47f506867a6cca");
        members.forEach(m-> System.out.println(m));
    }

    @Test
    public void testRedisCache(){
        List<CfImage> cfImages = cfImageService.finaAllCfImageForPage(1, 3);
        cfImages.forEach(c-> System.out.println(c));
    }
}
