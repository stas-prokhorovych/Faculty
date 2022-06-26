package com.example.model.dao;

import com.example.model.dao.exception.DAOException;
import com.example.model.entity.Course;
import com.example.model.utils.pagination.CourseCatalogueInfo;

import java.util.List;

public interface CourseDAO {

    void deleteCourse(int id) throws DAOException;

    void addCourse(Course course) throws DAOException;

    void updateCourse(Course course) throws DAOException;

    List<String> findThemes() throws DAOException;

    List<Course> getNoTeacherCourses() throws DAOException;

    List<Course> findAllFinishedCoursesByTeacherId(int teacherId) throws DAOException;

    List<Course> findRegisteredCoursesByStudentId(Integer studentId) throws DAOException;

    List<Course> findInProgressCoursesByStudentId(Integer studentId) throws DAOException;

    List<Course> findAllInProgressCoursesByTeacherId(Integer teacherId) throws DAOException;

    List<Course> findFinishedCoursesByStudentId(Integer studentId) throws DAOException;

    Course findCourseById(String id) throws DAOException;

    Course findCourseByName(String name) throws DAOException;

    CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String type, String theme, Integer teacherId, String sort, String order) throws DAOException;

    int numberOfRecords(String role, String theme, Integer teacherId) throws DAOException;

    List<Boolean> courseAlreadySelected(List<Course> courses, Integer studentId) throws DAOException;

    List<Course> finAllOpenForRegCoursesByTeacherId(Integer teacherId) throws DAOException;

    void startCourse(Integer courseId) throws DAOException;

    void assignCourse(String courseId, String studentId) throws DAOException;
}
