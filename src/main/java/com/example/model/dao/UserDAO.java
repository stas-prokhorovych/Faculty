package com.example.model.dao;

import com.example.model.dao.exception.DAOException;
import com.example.model.entity.User;

import java.util.List;

public interface UserDAO {

    /**
     * @param login login to find user by
     * @return user with such login
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    User findUserByLogin(String login) throws DAOException;

    /**
     * @param user user to add
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void addUser(User user) throws DAOException;

    /**
     * @param studentId student id to enroll
     * @param courseId course id to enroll
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void enrollStudentOnCourse(Integer studentId, Integer courseId) throws DAOException;

    /**
     * @param courseId id of course that finished
     * @return user of such course
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<User> findAllGraduates(Integer courseId) throws DAOException;

    /**
     * @param studentId id student to leave
     * @param courseId id course that student leave
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void leaveCourse(Integer studentId, Integer courseId) throws DAOException;

    /**
     * @param studentId student id that will be teacher
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void createTeacher(String studentId) throws DAOException;

    /**
     *
     * @param userTypeStudent
     * @return
     * @throws DAOException
     */
    List<User> getNewStudents(String userTypeStudent) throws DAOException;

    List<User> findByRole(String role) throws DAOException;

    void updateUserAccess(boolean access, String studentId) throws DAOException;
}
