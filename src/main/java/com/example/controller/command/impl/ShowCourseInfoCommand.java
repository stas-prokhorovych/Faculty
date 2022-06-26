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

import static com.example.model.constants.Pages.UPDATE_COURSE_PAGE;

public class ShowCourseInfoCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final CourseService courseService;
    private static final UserService userService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String id = request.getParameter("id");
        List<User> teachers = userService.getAllTeachers();
        Course course = courseService.findCourseById(id);
        request.setAttribute("course", course);
        request.setAttribute("teachers", teachers);

        return UPDATE_COURSE_PAGE;
    }
}
