package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.service.CourseService;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.STUDENT_COURSES;

public class StudentCourses implements Command {
    private static final ServiceFactory serviceFactory;
    private static final CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer studentId = (Integer) request.getSession(false).getAttribute("id");

        List<Course> registeredCourses = courseService.findRegisteredCoursesByStudentId(studentId);
        List<Course> inProgressCourses = courseService.findInProgressCoursesByStudentId(studentId);
        List<Course> finishedCourses = courseService.findFinishedCoursesByStudentId(studentId);

        request.setAttribute("registeredCourses", registeredCourses);
        request.setAttribute("inProgressCourses", inProgressCourses);
        request.setAttribute("finishedCourses", finishedCourses);

        return STUDENT_COURSES;
    }
}
