package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.COURSE_CATALOGUE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Enroll on course
 */
public class EnrollOnCourseCommand implements Command {
    private UserService userService;

    public EnrollOnCourseCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Integer studentId = Integer.valueOf(request.getParameter("student-id"));
        Integer courseId = Integer.valueOf(request.getParameter("course-id"));

        try {
            userService.enrollStudentOnCourse(studentId, courseId);
        } catch (UserServiceException e) {
            request.setAttribute("dataError", e.getMessage());
            return new CourseCatalogueCommand().execute(request, response);
        }

        return REDIRECT + COURSE_CATALOGUE_PAGE;
    }
}
