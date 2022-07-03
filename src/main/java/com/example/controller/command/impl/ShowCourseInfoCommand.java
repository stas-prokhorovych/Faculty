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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.UPDATE_COURSE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

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
        String id;
        HttpSession session = request.getSession(true);

        if(request.getParameter("id") != null) {
            id = request.getParameter("id");
        } else {
            id = (String) session.getAttribute("updateId");
        }

        List<User> teachers = userService.findByRole("Teacher");
        Course course = courseService.findCourseById(id);

        session.setAttribute("updateCourse", course);
        session.setAttribute("updateTeachers", teachers);
        session.setAttribute("updateId", id);

        return REDIRECT + UPDATE_COURSE_PAGE;
    }
}
