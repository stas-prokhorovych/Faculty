package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.model.constants.Pages.COURSE_CATALOGUE_PAGE;

public class CourseCatalogueCommand implements Command {
    private static ServiceFactory serviceFactory;
    private static CourseService courseService;
    private static UserService userService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> themesForForm = courseService.findThemes();
        List<User> teacherForForm = userService.getAllTeachers();
        request.setAttribute("themesForForm", themesForForm);
        request.setAttribute("teacherForForm", teacherForForm);

        String theme = request.getParameter("theme");
        Integer teacherId = null;
        if(request.getParameter("teacher") != null) {
            teacherId = Integer.parseInt(request.getParameter("teacher"));
        }

        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));

        }

        List<Course> courses = courseService.findAllCoursesByPage((page-1)*recordsPerPage, recordsPerPage, theme, teacherId, sort, order);
        List<Integer> studentsEnrolled = courseService.findUserEnrolled(courses);
        List<User> teachers = userService.findTeacherByCourse(courses);

        if(sort != null && sort.equals("sort-student-enrolled")) {
            Map<Course, Integer> map = Utils.sortByStudentEnrolled(courses, studentsEnrolled, order);
            courses = new ArrayList<>();
            studentsEnrolled = new ArrayList<>();

            for (Map.Entry<Course, Integer> entry : map.entrySet()) {
                courses.add(entry.getKey());
                studentsEnrolled.add(entry.getValue());
            }
        }

        request.setAttribute("courses", courses);
        request.setAttribute("theme", theme);
        request.setAttribute("nuOfStudents", studentsEnrolled);
        request.setAttribute("teachers", teachers);

        int noOfRecords = courseService.findNumberOfRecords(theme, teacherId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("courses", courses);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return COURSE_CATALOGUE_PAGE;
    }
}
