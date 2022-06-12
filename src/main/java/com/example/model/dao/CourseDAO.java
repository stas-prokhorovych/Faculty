package com.example.model.dao;

import com.example.model.entity.Course;

import java.util.List;

import static com.example.model.constants.Query.*;


public interface CourseDAO {


    List<Course> findAllCoursesByPage(int offset, int noOfRecords);

    int findNumberOfRecords();

    List<Course> findAllCoursesByThemeByPage(String theme, int offset, int noOfRecords);

    int findNumberOfRecordsByTheme(String theme);

    List<Course> findAllCoursesByTheme(String theme);


    void deleteCourse(int id);

    void addCourse(Course course);

    void updateCourse(String name, int id);

    String findName(int id);

    List<String> findThemes();
}
