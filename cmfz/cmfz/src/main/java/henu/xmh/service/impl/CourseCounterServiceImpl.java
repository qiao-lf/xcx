package henu.xmh.service.impl;

import henu.xmh.dao.CourseCounterMapper;
import henu.xmh.pojo.CourseCounter;
import henu.xmh.pojo.CourseCounterExample;
import henu.xmh.service.CourseCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CourseCounterServiceImpl implements CourseCounterService {
    @Autowired
    private CourseCounterMapper courseCounterMapper;

    /**
     * 查找该用户的该课程下的所有计数器
     * @param userId
     * @param courseId
     * @return
     */
    @Override
    public List<CourseCounter> findCourseCounterByUserIdAndCourseId(String userId, String courseId) {
        CourseCounterExample example = new CourseCounterExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andUserIdEqualTo(userId);
        return courseCounterMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(CourseCounter courseCounter) {
        if(courseCounter.getCourseId()==null)throw new RuntimeException("添加失败！课程ID为空！");
        if(courseCounter.getUserId()==null)throw new RuntimeException("添加失败！用户ID为空！");
        courseCounter.setCounterId(UUID.randomUUID().toString().replace("-",""))//计数器主键
                .setCreateTime(new Date()).setCounterNum(0);//创建时间和初始数为0
        courseCounterMapper.insertSelective(courseCounter);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void drop(CourseCounter courseCounter) {
        if(courseCounter.getCourseId()==null)throw new RuntimeException("删除失败！课程ID为空！");
        if(courseCounter.getUserId()==null)throw new RuntimeException("删除失败！用户ID为空！");
        courseCounterMapper.deleteByPrimaryKey(courseCounter.getCounterId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void alter(CourseCounter courseCounter) {
        courseCounterMapper.updateByPrimaryKeySelective(courseCounter);
    }
}
