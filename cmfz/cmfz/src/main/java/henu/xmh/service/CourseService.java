package henu.xmh.service;

import henu.xmh.pojo.Course;

import java.util.List;

public interface CourseService {

    void add(Course course);

    void drop(Course course);

    void alter(Course course);

    List<Course> findAllByUserId(String userId);
}
