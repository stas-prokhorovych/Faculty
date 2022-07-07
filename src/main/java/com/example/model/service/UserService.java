package com.example.model.service;

import com.example.model.entity.User;
import com.example.model.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    /**
     * @param login login of user
     * @param password password of user
     * @return user with such login and password
     * @throws ServiceException dao layer error or validation error
     */
    User getUser(String login, String password) throws ServiceException;

    /**
     * @param user to add
     * @throws ServiceException dao layer error or validation error
     */
    void addUser(User user) throws ServiceException;

    /**
     * @param studentId student to start study
     * @param courseId course that student will be study
     * @throws ServiceException dao layer error or validation error
     */
    void enrollStudentOnCourse(Integer studentId, Integer courseId) throws ServiceException;

    /**
     * @param courseId course that finished
     * @return students that finish this course
     * @throws ServiceException dao layer error or validation error
     */
    List<User> findAllGraduates(Integer courseId) throws ServiceException;

    /**
     * @param login login of the user
     * @throws ServiceException dao layer error or validation error
     */
    void findUserByLogin(String login) throws ServiceException;

    /**
     * @param studentId if of student that will leave course
     * @param courseId course that student will leave
     * @throws ServiceException dao layer error or validation error
     */
    void leaveCourse(Integer studentId, Integer courseId) throws ServiceException;

    /**
     * @param studentId student that ought to become teacher
     * @throws ServiceException dao layer error or validation error
     */
    void createTeacher(String studentId) throws ServiceException;

    /**
     * @param userTypeStudent student type
     * @return newly created user
     * @throws ServiceException dao layer error or validation error
     */
    List<User> getNewStudents(String userTypeStudent) throws ServiceException;

    /**
     * @param role specified role
     * @return user with such role
     * @throws ServiceException dao layer error or validation error
     */
    List<User> findByRole(String role) throws ServiceException;

    /**
     * @param access access that will be changed
     * @param studentId student that access will be changed
     * @throws ServiceException dao layer error or validation error
     */
    void updateUserAccess(boolean access, String studentId) throws ServiceException;
}
