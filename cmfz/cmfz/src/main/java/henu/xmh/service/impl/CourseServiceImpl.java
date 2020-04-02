package henu.xmh.service.impl;

import henu.xmh.dao.CourseMapper;
import henu.xmh.pojo.Course;
import henu.xmh.pojo.CourseExample;
import henu.xmh.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(Course course) {
        if(course.getUserId()==null) throw new RuntimeException("添加失败!用户ID为空");
        course.setCourseId(UUID.randomUUID().toString().replace("-",""))
            .setCourseStyle("2").setCreateTime(new Date());
        courseMapper.insertSelective(course);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void drop(Course course) {
        if(course.getUserId()==null) throw new RuntimeException("删除失败!用户ID为空");
        courseMapper.deleteByPrimaryKey(course.getCourseId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void alter(Course course) {
        courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public List<Course> findAllByUserId(String userId) {
        CourseExample example = new CourseExample();
        example.createCriteria().andUserIdEqualTo(userId);//该用户的课程
        return courseMapper.selectByExample(example);
    }
}
