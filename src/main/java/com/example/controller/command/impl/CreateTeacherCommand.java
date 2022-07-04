package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.HOME_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Create teacher command
 */
public class CreateTeacherCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final UserService userService;
    private static final CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String studentId = request.getParameter("students");
        String courseId = request.getParameter("courses");

        userService.createTeacher(studentId);
        courseService.assignCourse(courseId, studentId);

        return REDIRECT + HOME_PAGE;
    }
}
