package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.CourseService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartCourseCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Integer courseId = Integer.valueOf(request.getParameter("course-id"));

        courseService.startCourse(courseId);

        return new ShowJournalCommand().execute(request, response);
    }
}