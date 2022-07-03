package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.CourseCatalogueInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        List<String> themesForForm = courseService.findThemes();
        request.setAttribute("themesForForm", themesForForm);

        List<User> teacherForForm = userService.findByRole("Teacher");

        request.setAttribute("teacherForForm", teacherForForm);

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

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        int page = START_PAGE;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;
        if(request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            request.setAttribute("recordsPerPage", recordsPerPage);
        }

        CourseCatalogueInfo catalogueInfo = courseService.findCoursesByPage((page-1)*recordsPerPage, recordsPerPage, role, theme, teacherId, sort, order);

        List<Course> courses = catalogueInfo.getCourses();
        List<Integer> studentsEnrolled = catalogueInfo.getStudentsEnrolled();
        List<User> teachers = catalogueInfo.getTeachers();

        if(role != null && role.equals("Student")) {
            Integer studentId = (Integer) session.getAttribute("id");
            List<Boolean> courseAlreadySelected = courseService.courseAlreadySelected(courses, studentId);
            request.setAttribute("courseAlreadySelected", courseAlreadySelected);
        }

        int noOfRecords = courseService.numberOfRecords(role, theme, teacherId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("courses", courses);
        request.setAttribute("nuOfStudents", studentsEnrolled);
        request.setAttribute("teachers", teachers);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfPages", noOfPages);

        return COURSE_CATALOGUE_PAGE;
    }
}
