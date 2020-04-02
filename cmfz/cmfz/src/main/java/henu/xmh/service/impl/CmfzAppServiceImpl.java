package henu.xmh.service.impl;

import henu.xmh.dao.*;
import henu.xmh.pojo.*;
import henu.xmh.service.*;
import henu.xmh.util.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CmfzAppServiceImpl implements CmfzAppService {
    @Autowired
    private CfAlbumService cfAlbumService;
    @Autowired
    private CfAlbumMapper cfAlbumMapper;
    @Autowired
    private CfImageService cfImageService;
    @Autowired
    private EssayService essayService;
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private CfUserService cfUserService;
    @Autowired
    private CfUserMapper cfUserMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCounterService courseCounterService;
    @Autowired
    private GuruService guruService;
    @Autowired
    private CfUserAboutGuruService cfUserAboutGuruService;


    @Override
    public Map<String, Object> findHome(String userId, String type, String sub_type) {
        Map<String,Object> map = new HashMap<>();
        try {
            if("all".equals(type)){//首页数据显示
                List<CfImage> cfImages = cfImageService.finaAllCfImageForPage(1, 5);
                map.put("cfImages",cfImages);//图片信息
                Map<String, Object> allForPage = cfAlbumService.findAllForPage(1, 6);
                map.put("cfAlbums",allForPage.get("rows"));//专辑信息
                Map<String, Object> allEssayForPage = essayService.findAllEssayForPage(1, 3);
                map.put("essays",allEssayForPage.get("rows"));//文章信息
            }else if("wen".equals(type)){//专辑页显示数据
                List<CfAlbum> cfAlbums = cfAlbumMapper.selectByExample(new CfAlbumExample());
                map.put("cfAlbums",cfAlbums);//所有专辑信息
            }else if("si".equals(type)&&"ssyj".equals(sub_type)){//上师言教,显示用户关注的上师的文章

            }else if("si".equals(type)&&"xmfy".equals(sub_type)){//显密法要,显示所有文章
                List<Essay> essays = essayMapper.selectByExample(new EssayExample());
                map.put("essays",essays);
            }
            map.put("status",200);//状态码

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);
            map.put("error","查询失败");
        }
        return map;
    }


    @Override
    public Map<String, Object> findCfAlbumDetail(String albumId) {
        Map<String, Object> map =  new HashMap<>();
        CfAlbum cfAlbum = cfAlbumMapper.selectByPrimaryKey(albumId);
        //查找该专辑下的所有章节信息
        ChapterExample example = new ChapterExample();
        ChapterExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumIdEqualTo(albumId);
        List<Chapter> chapters = chapterMapper.selectByExample(example);
        map.put("albumldIntroduction",cfAlbum);//该专辑的详细信息
        map.put("chapterlist",chapters);//该专辑下的所有章节
        return map;
    }

    @Override
    public Essay findEssayDetail(String essayId) {
        Essay essay = essayMapper.selectByPrimaryKey(essayId);
        return essay;
    }


    /**
     * 发送验证码,返回值为验证码
     * @param mobilePhone
     * @return
     */
    @Override
    public String sendMail(String mobilePhone) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String code = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        valueOperations.set( mobilePhone,code);//一分钟有效
        SendMailUtil.sendMail(mobilePhone,code);//发送手机验证码
        redisTemplate.expire(mobilePhone,60,TimeUnit.SECONDS);//设置验证码超时时间
        return code;
    }

    @Override
    public CfUser loginByPwd(String mobilePhone, String password) {
        CfUser cfUser = cfUserService.findOneByMobilePhone(mobilePhone);
        if(cfUser==null) throw new RuntimeException("手机号不存在!");//手机号不存在
        if(!cfUser.getPassword().equals(password)) throw new RuntimeException("密码输入错误！");//密码错误
        return cfUser;//登录成功
    }

    @Override
    public CfUser loginByCode(String mobilePhone, String code) {
        CfUser cfUser = cfUserService.findOneByMobilePhone(mobilePhone);
        if(cfUser==null) throw new RuntimeException("手机号不存在!");//手机号不存在
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String codeIn = (String)valueOperations.get(mobilePhone);//获取验证码
        if(codeIn==null) throw new RuntimeException("验证码已过期！");
        if(!codeIn.equalsIgnoreCase(code))throw new RuntimeException("验证码输入错误！");
        //向下执行登录成功
        return cfUser;
    }

    @Override
    public CfUser register(String mobilePhone, String password) {
        //验证用户是否已存在
        CfUser cfUserServiceOneByMobilePhone = cfUserService.findOneByMobilePhone(mobilePhone);
        if(cfUserServiceOneByMobilePhone!=null) throw  new RuntimeException("手机号已注册!");
        CfUser cfUser = new CfUser().setMobilePhone(mobilePhone).setPassword(password)//密码暂未加盐
                .setLocation("北京").setSex("1").setUserId(UUID.randomUUID().toString().replace("-", ""))
                .setRegister(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//设置注册时间
        cfUserService.add(cfUser);//添加用户
        return cfUser;
    }

    @Override
    public CfUser updateCfUser(CfUser cfUser) {
        cfUserMapper.updateByPrimaryKeySelective(cfUser);
        CfUser result = cfUserMapper.selectByPrimaryKey(cfUser.getUserId());
        return result;
    }

    @Override
    public Map<String, Object> addCourse(Course course) {
        Map<String, Object> map = new HashMap<>();
        try {
            courseService.add(course);
            List<Course> allByUserId = courseService.findAllByUserId(course.getUserId());
            map.put("status",200);
            map.put("option",allByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> findAllCourse(String userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Course> allByUserId = courseService.findAllByUserId(userId);
            map.put("status",200);
            map.put("option",allByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);
            map.put("message","展示失败！");
        }
        return map;
    }

    @Override
    public Map<String, Object> dropCourse(Course course) {
        Map<String, Object> map = new HashMap<>();
        try {
            courseService.drop(course);//删除用户
            List<Course> allByUserId = courseService.findAllByUserId(course.getUserId());
            map.put("status",200);//删除成功
            map.put("option",allByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);//删除失败
            map.put("message",e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> dropCounter(CourseCounter courseCounter) {
        Map<String, Object> map = new HashMap<>();
        try {
            courseCounterService.drop(courseCounter);//删除计数器
            List<CourseCounter> courses = courseCounterService.findCourseCounterByUserIdAndCourseId(courseCounter.getUserId(), courseCounter.getCourseId());
            map.put("status",200);//删除成功
            map.put("option",courses);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);//删除失败
            map.put("message",e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> findCounter(CourseCounter courseCounter) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<CourseCounter> courses = courseCounterService.findCourseCounterByUserIdAndCourseId(courseCounter.getUserId(), courseCounter.getCourseId());
            map.put("status",200);//删除成功
            map.put("option",courses);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);//删除失败
            map.put("message",e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> alterCounter(CourseCounter courseCounter) {
        Map<String, Object> map = new HashMap<>();
        try {
            courseCounterService.alter(courseCounter);//修改计数器
            List<CourseCounter> courses = courseCounterService.findCourseCounterByUserIdAndCourseId(courseCounter.getUserId(), courseCounter.getCourseId());
            map.put("status",200);//删除成功
            map.put("option",courses);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);//删除失败
            map.put("message",e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> addCounter(CourseCounter courseCounter) {
        Map<String, Object> map = new HashMap<>();
        try {
            courseCounterService.add(courseCounter);//添加计数器
            List<CourseCounter> courses = courseCounterService.findCourseCounterByUserIdAndCourseId(courseCounter.getUserId(), courseCounter.getCourseId());
            map.put("status",200);//删除成功
            map.put("option",courses);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);//删除失败
            map.put("message",e.getMessage());
        }
        return map;
    }

    /**
     * 关注上师
     * @param userId
     * @param guruId
     * @return
     */
    @Override
    public List<Guru> aboutGuru(String userId, String guruId) {
        cfUserAboutGuruService.aboutGuru(userId,guruId);
        return guruService.findAll();
    }

    /**
     * 展示上市
     * @param userId
     * @return
     */
    @Override
    public List<Guru> findGuru(String userId) {
        List<Guru> all = guruService.findAll();
        return all;
    }

    /**
     * 推荐好友
     * @param userId
     * @return
     */
    @Override
    public List<CfUser> recommendCfUser(String userId) {
        return cfUserAboutGuruService.recommendCfUser(userId);
    }


}
