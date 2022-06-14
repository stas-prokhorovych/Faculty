package com.example.model.service;

import com.example.model.entity.Course;
import com.example.model.entity.User;

import java.util.List;

public interface CourseService {
    void deleteCourse(int id);

    void addCourse(Course course);

    List<String> findThemes();

    List<Course> findAllCoursesByPage(int offset, int noOfRecords, String theme, Integer teacher, String sort, String order);

    int findNumberOfRecords(String theme, Integer teacher);

    void updateCourse(String name, int id);

    List<Course> getNoTeacherCourses();

    List<Integer> findUserEnrolled(List<Course> courses);
}
