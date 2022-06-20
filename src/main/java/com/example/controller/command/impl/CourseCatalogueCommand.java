package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.pagination.CourseCatalogueInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.COURSE_CATALOGUE_PAGE;

public class CourseCatalogueCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final CourseService courseService;
    private static final UserService userService;
    private static final int DEFAULT_RECORDS_PER_PAGE = 5;
    private static final int START_PAGE = 1;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // display info in filter fields
        List<String> themesForForm = courseService.findThemes();
        List<User> teacherForForm = userService.getAllTeachers();
        request.setAttribute("themesForForm", themesForForm);
        request.setAttribute("teacherForForm", teacherForForm);

        // filter preferences if exists safe for pagination
        String type = null;
        if(request.getParameter("type") != null) {
            type = request.getParameter("type");
            request.setAttribute("type", type);
        }
        String theme = null;
        if(request.getParameter("theme") != null) {
            theme = request.getParameter("theme");
            request.setAttribute("theme", theme);
        }
        Integer teacherId = null;
        if(request.getParameter("teacher") != null) {
            teacherId = Integer.parseInt(request.getParameter("teacher"));
        }
        String sort = request.getParameter("sort");
        if(sort != null) {
            request.setAttribute("sort", sort);
        }
        String order = request.getParameter("order");
        if(order != null) {
            request.setAttribute("order", order);
        }
        int page = START_PAGE;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;
        if(request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            request.setAttribute("recordsPerPage", recordsPerPage);
        }

        CourseCatalogueInfo catalogueInfo = courseService.findCoursesByPage((page-1)*recordsPerPage, recordsPerPage, type, theme, teacherId, sort, order);

        List<Course> courses = catalogueInfo.getCourses();
        List<Integer> studentsEnrolled = catalogueInfo.getStudentsEnrolled();
        List<User> teachers = catalogueInfo.getTeachers();
        request.setAttribute("courses", courses);
        request.setAttribute("nuOfStudents", studentsEnrolled);
        request.setAttribute("teachers", teachers);

        // change find number of records
        int noOfRecords = courseService.findNumberOfRecords(theme, teacherId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        //request.setAttribute("courses", courses);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return COURSE_CATALOGUE_PAGE;
    }
}
