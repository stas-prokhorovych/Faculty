package com.example.model.dao;

import com.example.model.entity.Course;
import com.example.model.utils.pagination.CourseCatalogueInfo;

import java.util.List;

public interface CourseDAO {
    int findNumberOfRecords();

    int findNumberOfRecordsByTheme(String theme);

    void deleteCourse(int id);

    void addCourse(Course course);

    void updateCourse(String name, int id);

    List<String> findThemes();

    List<Course> getNoTeacherCourses();

    int findNumberOfRecordsByTeacher(Integer teacher);

    List<Course> findAllFinishedCoursesByTeacherId(int teacherId);

    List<Course> findRegisteredCoursesByStudentId(Integer studentId);

    List<Course> findInProgressCoursesByStudentId(Integer studentId);

    List<Course> findAllInProgressCoursesByTeacherId(Integer teacherId);

    List<Course> findFinishedCoursesByStudentId(Integer studentId);

    Course findCourseById(String id);

    Course findCourseByName(String name);

    CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String type, String theme, Integer teacherId, String sort, String order);
}
