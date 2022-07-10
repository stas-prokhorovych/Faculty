package com.example.model.dao;

import com.example.model.dao.exception.DAOException;
import com.example.model.entity.Course;
import com.example.model.utils.CourseCatalogueInfo;

import java.util.List;

public interface CourseDAO {

    /**
     * Delete course by course id
     *
     * @param id an if of course to be deleted
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void deleteCourse(int id) throws DAOException;

    /**
     * Add course
     *
     * @param course course to be added
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void addCourse(Course course) throws DAOException;

    /**
     * Update specified course
     *
     * @param course course to be updated
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void updateCourse(Course course) throws DAOException;

    /**
     * Find distinct themes that exist
     *
     * @return a list of themes
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<String> findThemes() throws DAOException;

    /**
     * Find course by id
     *
     * @param id id of course
     * @return course with given id
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    Course findCourseById(String id) throws DAOException;

    /**
     * Find course by name
     *
     * @param name name of the course
     * @return course with give name
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    Course findCourseByName(String name) throws DAOException;

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
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String role, String theme, Integer teacherId, String sort, String order) throws DAOException;

    /**
     * Used for getting number of records
     * for displaying pagination buttons
     *
     * @param role role of user Student, Teacher, Admin, Guest
     * @param theme theme of course if selected
     * @param teacherId specified teacher if selected
     * @return number of records for specified parameters
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    int numberOfRecords(String role, String theme, Integer teacherId) throws DAOException;

    /**
     * Check if each course selected
     * used for Student role
     * to show enroll/leave option
     *
     * @param courses list of courses by page
     * @param studentId id of logged student
     * @return return true if course selected or false otherwise
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<Boolean> courseAlreadySelected(List<Course> courses, Integer studentId) throws DAOException;

    /**
     * Assign course for student
     * after assignment became teacher
     *
     * @param courseId id of course to assign
     * @param studentId student id that became teacher
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void assignCourse(String courseId, String studentId) throws DAOException;

    /**
     * Used for journal
     * to show course of teacher by status
     *
     * @param teacherId id of specified logged teacher
     * @param status status of course
     * @return return course with specified teacherId and status
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<Course> findCoursesByTeacherAndStatus(Integer teacherId, String status) throws DAOException;

    /**
     * Used for your courses
     * to show course of student by status
     *
     * @param studentId id of specified logged student
     * @param status status of course
     * @return return course with specified studentId and status
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<Course> findStudentCoursesByStatus(Integer studentId, String status) throws DAOException;

    /**
     * Start course
     * change date to current
     *
     * @param status new status of the course
     * @param courseId id of specified course
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void startCourse(String status, Integer courseId) throws DAOException;

    /**
     * Find course with no teacher assigned
     *
     * @param status status of courses
     * @return return all lists with no teachers assigned yet
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<Course> findCoursesNoTeacherAssigned(String status) throws DAOException;

    boolean courseAlreadyStart(String status, Integer courseId) throws DAOException;

    boolean checkCourseAlreadyEnded(String courseId) throws DAOException;
}
