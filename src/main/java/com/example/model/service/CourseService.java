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

    List<Course> getNoTeacherCourses() throws ServiceException;

    List<Course> findAllFinishedCoursesByTeacherId(int teacherId) throws ServiceException;

    List<Course> findRegisteredCoursesByStudentId(Integer studentId) throws ServiceException;

    List<Course> findInProgressCoursesByStudentId(Integer studentId) throws ServiceException;

    List<Course> findAllInProgressCoursesByTeacherId(Integer teacherId) throws ServiceException;

    List<Course> findFinishedCoursesByStudentId(Integer studentId) throws ServiceException;

    Course findCourseById(String id) throws ServiceException;

    CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String type, String theme, Integer teacherId, String sort, String order) throws ServiceException;

    int numberOfRecords(String role, String theme, Integer teacherId) throws ServiceException;

    List<Boolean> courseAlreadySelected(List<Course> courses, Integer studentId) throws ServiceException;

    List<Course> finAllOpenForRegCoursesByTeacherId(Integer teacherId) throws ServiceException;

    void startCourse(Integer courseId) throws ServiceException;

    void assignCourse(String courseId, String studentId) throws ServiceException;
}
