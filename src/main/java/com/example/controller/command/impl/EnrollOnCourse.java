package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EnrollOnCourse implements Command {
    private static ServiceFactory serviceFactory;
    private static UserService userService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer studentId = Integer.valueOf(request.getParameter("student-id"));
        Integer courseId = Integer.valueOf(request.getParameter("course-id"));

        userService.enrollStudentOnCourse(studentId, courseId);

        return new CourseCatalogueCommand().execute(request, response);
    }
}
