package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.CourseService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.HOME_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Assign teacher to course command
 */
public class AssignTeacherToCourseCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String courseId = request.getParameter("courses");
        String teacherId = request.getParameter("teachers");
        courseService.assignCourse(courseId, teacherId);

        return REDIRECT + HOME_PAGE;
    }
}
