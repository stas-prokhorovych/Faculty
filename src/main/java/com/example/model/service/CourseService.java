package com.example.model.service;

import com.example.model.entity.Course;
import com.example.model.service.exception.ServiceException;
import com.example.model.utils.CourseCatalogueInfo;

import java.util.List;

public interface CourseService {
    void deleteCourse(int id) throws ServiceException;

    void addCourse(Course course) throws ServiceException;

    List<String> findThemes() throws ServiceException;

    void updateCourse(Course course, String previousName) throws ServiceException;

    Course findCourseById(String id) throws ServiceException;

    CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String type, String theme, Integer teacherId, String sort, String order) throws ServiceException;

    int numberOfRecords(String role, String theme, Integer teacherId) throws ServiceException;

    List<Boolean> courseAlreadySelected(List<Course> courses, Integer studentId) throws ServiceException;

    void assignCourse(String courseId, String studentId) throws ServiceException;

    List<Course> findCoursesByTeacherAndStatus(Integer teacherId, String status) throws ServiceException;

    List<Course> findStudentCoursesByStatus(Integer studentId, String status) throws ServiceException;

    void startCourse(String status, Integer courseId) throws ServiceException;

    List<Course> findCoursesNoTeacherAssigned(String status) throws ServiceException;
}
