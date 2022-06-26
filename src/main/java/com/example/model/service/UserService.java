package com.example.model.service;

import com.example.model.entity.User;
import com.example.model.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User getUser(String login, String password) throws ServiceException;

    void addUser(User user) throws ServiceException;

    List<User> getAllStudents() throws ServiceException;

    List<User> getAllTeachers() throws ServiceException;

    void enrollStudentOnCourse(Integer studentId, Integer courseId) throws ServiceException;

    void blockUser(String studentId) throws ServiceException;

    List<User> findAllGraduates(Integer courseId) throws ServiceException;

    void unblockUser(String studentId) throws ServiceException;

    void findUserByLogin(String login) throws ServiceException;

    void leaveCourse(Integer studentId, Integer courseId) throws ServiceException;

    List<User> getNewStudents() throws ServiceException;

    void createTeacher(String studentId) throws ServiceException;

    User findUserById(String studentId) throws ServiceException;
}
