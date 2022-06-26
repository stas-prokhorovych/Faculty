package com.example.model.dao;

import com.example.model.dao.exception.DAOException;
import com.example.model.entity.User;

import java.util.List;

public interface UserDAO {

    User findUserByLogin(String login) throws DAOException;

    void addUser(User user) throws DAOException;

    List<User> findTeachers() throws DAOException;

    List<User> findStudents() throws DAOException;

    void enrollStudentOnCourse(Integer studentId, Integer courseId) throws DAOException;

    void blockUser(String studentId) throws DAOException;

    List<User> findAllGraduates(Integer courseId) throws DAOException;

    void unblockUser(String studentId) throws DAOException;

    void leaveCourse(Integer studentId, Integer courseId) throws DAOException;

    List<User> getNewStudents() throws DAOException;

    void createTeacher(String studentId) throws DAOException;

    User findUserById(String studentId) throws DAOException;
}
