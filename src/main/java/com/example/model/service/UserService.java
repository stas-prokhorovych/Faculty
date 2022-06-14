package com.example.model.service;

import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.exception.ServiceException;

import java.util.List;

public interface UserService {
    User getUser(String login, String password) throws ServiceException;

    void addUser(User user);

    List<User> getAllStudents();

    List<User> getAllTeachers();

    List<User> findTeacherByCourse(List<Course> courses);

    void enrollStudentOnCourse(Integer studentId, Integer courseId);
}
