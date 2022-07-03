package com.example.model.dao;

import com.example.model.dao.exception.DAOException;
import com.example.model.entity.User;

import java.util.List;

public interface UserDAO {

    User findUserByLogin(String login) throws DAOException;

    void addUser(User user) throws DAOException;

    void enrollStudentOnCourse(Integer studentId, Integer courseId) throws DAOException;

    List<User> findAllGraduates(Integer courseId) throws DAOException;

    void leaveCourse(Integer studentId, Integer courseId) throws DAOException;

    void createTeacher(String studentId) throws DAOException;

    List<User> getNewStudents(String userTypeStudent) throws DAOException;

    List<User> findByRole(String role) throws DAOException;

    void updateUserAccess(boolean access, String studentId) throws DAOException;
}
