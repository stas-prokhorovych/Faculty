package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.COURSE_CATALOGUE_PAGE;

public class LeaveCourseCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final UserService userService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Integer studentId = Integer.valueOf(request.getParameter("student-id"));
        Integer courseId = Integer.valueOf(request.getParameter("course-id"));

        userService.leaveCourse(studentId, courseId);

        return "redirect:" + COURSE_CATALOGUE_PAGE;
    }
}
