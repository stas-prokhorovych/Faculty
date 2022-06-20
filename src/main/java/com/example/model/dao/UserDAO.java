package com.example.model.dao;

import com.example.model.entity.User;

import java.util.List;

public interface UserDAO {

    User findUserByLogin(String login);

    void addUser(User user);

    List<User> findTeachers();

    List<User> findStudents();

    void enrollStudentOnCourse(Integer studentId, Integer courseId);

    void blockUser(String studentId);

    List<User> findAllGraduates(Integer courseId);

    void unblockUser(String studentId);
}
