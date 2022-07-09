package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.CourseService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.SHOW_JOURNAL_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Start course command
 */
public class StartCourseCommand implements Command {
    private CourseService courseService;

    public StartCourseCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Integer courseId = Integer.valueOf(request.getParameter("course-id"));
        courseService.startCourse("In progress", courseId);
        return REDIRECT + SHOW_JOURNAL_PAGE;
    }
}
