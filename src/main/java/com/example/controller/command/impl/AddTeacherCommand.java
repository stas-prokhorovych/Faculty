package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.ADD_TEACHER_PAGE;

/**
 * Add teacher command
 */
public class AddTeacherCommand implements Command {
    private UserService userService;
    private CourseService courseService;

    public AddTeacherCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
        courseService = serviceFactory.getCourseService();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        List<User> students = userService.getNewStudents("Student");
        List<Course> courses = courseService.findCoursesNoTeacherAssigned("Closed, no teacher assigned yet");
        List<User> teachers = userService.findByRole("Teacher");

        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.setAttribute("teachers", teachers);

        return ADD_TEACHER_PAGE;
    }
}
