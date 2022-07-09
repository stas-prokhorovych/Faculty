package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.CourseService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.COURSE_CATALOGUE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Delete course command
 */
public class DeleteCourseCommand implements Command {

    private CourseService courseService;

    public DeleteCourseCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String id = request.getParameter("id");
        courseService.deleteCourse(Integer.parseInt(id));
        return REDIRECT + COURSE_CATALOGUE_PAGE;
    }
}
