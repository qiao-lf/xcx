package henu.xmh.service;

import henu.xmh.pojo.CourseCounter;

import java.util.List;

public interface CourseCounterService {
    List<CourseCounter> findCourseCounterByUserIdAndCourseId(String userId,String courseId);

    void add(CourseCounter courseCounter);

    void drop(CourseCounter courseCounter);

    void alter(CourseCounter courseCounter);
}
