package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.ADD_TEACHER_PAGE;
import static com.example.model.constants.Pages.USER_CATALOGUE_PAGE;

public class AddTeacherCommand implements Command {
    private static ServiceFactory serviceFactory;
    private static UserService userService;
    private static CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String student = request.getParameter("students");
        String course = request.getParameter("courses");

        if(student == null || course == null) {
            List<User> students = userService.getAllStudents();
            List<Course> courses = courseService.getNoTeacherCourses();
            request.setAttribute("students", students);
            request.setAttribute("courses", courses);
            return ADD_TEACHER_PAGE;
        }

//        userService.registerTeacher();

        return new UserCatalogueCommand().execute(request, response);
    }
}