package com.example.model.service;

import com.example.model.entity.Course;
import com.example.model.exception.CourseServiceException;
import com.example.model.exception.UserServiceException;

import java.util.List;

public interface CourseService {
    void deleteCourse(int id);

    void addCourse(Course course) throws CourseServiceException;

    List<String> findThemes();

    List<Course> findAllCoursesByPage(int offset, int noOfRecords, String theme, Integer teacher, String sort, String order);

    int findNumberOfRecords(String theme, Integer teacher);

    void updateCourse(String name, int id);

    List<Course> getNoTeacherCourses();

    List<Integer> findUserEnrolled(List<Course> courses);

    List<Course> findAllFinishedCoursesByTeacherId(int teacherId);

    List<Course> findRegisteredCoursesByStudentId(Integer studentId);

    List<Course> findInProgressCoursesByStudentId(Integer studentId);

    List<Course> findAllInProgressCoursesByTeacherId(Integer teacherId);

    List<Course> findFinishedCoursesByStudentId(Integer studentId);

    Course findCourseById(String id);
}
