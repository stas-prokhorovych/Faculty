package com.example.model.service;

import com.example.model.entity.User;
import com.example.model.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User getUser(String login, String password) throws ServiceException;

    void addUser(User user) throws ServiceException;

    void enrollStudentOnCourse(Integer studentId, Integer courseId) throws ServiceException;

    List<User> findAllGraduates(Integer courseId) throws ServiceException;

    void findUserByLogin(String login) throws ServiceException;

    void leaveCourse(Integer studentId, Integer courseId) throws ServiceException;

    void createTeacher(String studentId) throws ServiceException;

    List<User> getNewStudents(String userTypeStudent) throws ServiceException;

    List<User> findByRole(String role) throws ServiceException;

    void updateUserAccess(boolean access, String studentId) throws ServiceException;
}
