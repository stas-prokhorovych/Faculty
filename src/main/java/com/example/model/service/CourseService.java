package com.example.model.service;

import com.example.model.entity.Course;
import com.example.model.service.exception.ServiceException;
import com.example.model.utils.CourseCatalogueInfo;

import java.util.List;

public interface CourseService {
    /**
     * Delete course by course id
     *
     * @param id an if of course to be deleted
     * @throws ServiceException dao layer error or validation error
     */
    void deleteCourse(int id) throws ServiceException;

    /**
     * Add course
     *
     * @param course course to be added
     * @throws ServiceException dao layer error or validation error
     */
    void addCourse(Course course) throws ServiceException;

    /**
     * Find distinct themes that exist
     *
     * @return a list of themes
     * @throws ServiceException dao layer error or validation error
     */
    List<String> findThemes() throws ServiceException;

    /**
     * Update specified course
     *
     * @param course course to be updated
     * @throws ServiceException dao layer error or validation error
     */
    void updateCourse(Course course, String previousName) throws ServiceException;

    /**
     * Find course by id
     *
     * @param id id of course
     * @return course with given id
     * @throws ServiceException dao layer error or validation error
     */
    Course findCourseById(String id) throws ServiceException;

    /**
     * Used for pagination
     *
     * @param offset offset for data, by page
     * @param recordsPerPage record per page
     * @param role user type(Admin, Teacher, Student, Guest)
     * @param theme theme if specified
     * @param teacherId specified teacher if selected
     * @param sort sort parameter if selected
     * @param order order of sorting if selected
     * @return return CourseCatalogueInfo consist of Courses, Teachers, Student Enrolled
     * @throws ServiceException dao layer error or validation error
     */
    CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String role, String theme, Integer teacherId, String sort, String order) throws ServiceException;

    /**
     * Used for getting number of records
     * for displaying pagination buttons
     *
     * @param role role of user Student, Teacher, Admin, Guest
     * @param theme theme of course if selected
     * @param teacherId specified teacher if selected
     * @return number of records for specified parameters
     * @throws ServiceException dao layer error or validation error
     */
    int numberOfRecords(String role, String theme, Integer teacherId) throws ServiceException;

    /**
     * Check if each course selected
     * used for Student role
     * to show enroll/leave option
     *
     * @param courses list of courses by page
     * @param studentId id of logged student
     * @return return true if course selected or false otherwise
     * @throws ServiceException dao layer error or validation error
     */
    List<Boolean> courseAlreadySelected(List<Course> courses, Integer studentId) throws ServiceException;

    /**
     * Assign course for student
     * after assignment became teacher
     *
     * @param courseId id of course to assign
     * @param studentId student id that became teacher
     * @throws ServiceException dao layer error or validation error
     */
    void assignCourse(String courseId, String studentId) throws ServiceException;

    /**
     * Used for journal
     * to show course of teacher by status
     *
     * @param teacherId id of specified logged teacher
     * @param status status of course
     * @return return course with specified teacherId and status
     * @throws ServiceException dao layer error or validation error
     */
    List<Course> findCoursesByTeacherAndStatus(Integer teacherId, String status) throws ServiceException;

    /**
     * Used for your courses
     * to show course of student by status
     *
     * @param studentId id of specified logged student
     * @param status status of course
     * @return return course with specified studentId and status
     * @throws ServiceException dao layer error or validation error
     */
    List<Course> findStudentCoursesByStatus(Integer studentId, String status) throws ServiceException;

    /**
     * Start course
     * change date to current
     *
             * @param status new status of the course
     * @param courseId id of specified course
     * @throws ServiceException dao layer error or validation error
     */
    void startCourse(String status, Integer courseId) throws ServiceException;

    /**
     * Find course with no teacher assigned
     *
     * @param status status of courses
     * @return return all lists with no teachers assigned yet
     * @throws ServiceException dao layer error or validation error
     */
    List<Course> findCoursesNoTeacherAssigned(String status) throws ServiceException;
}
