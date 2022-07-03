package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.service.CourseService;
import com.example.model.service.JournalService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.SHOW_JOURNAL_PAGE;

public class ShowJournalCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Integer teacherId = (Integer) request.getSession(false).getAttribute("id");

        List<Course> openForRegCourses = courseService.findCoursesByTeacherAndStatus(teacherId, "Opened for registration");
        List<Course> inProgressCourses = courseService.findCoursesByTeacherAndStatus(teacherId, "In progress");
        List<Course> finishedCourses =  courseService.findCoursesByTeacherAndStatus(teacherId, "Finished");

        request.setAttribute("openForRegCourses", openForRegCourses);
        request.setAttribute("inProgressCourses", inProgressCourses);
        request.setAttribute("finishedCourses", finishedCourses);

        return SHOW_JOURNAL_PAGE;
    }
}
