package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.dao.mysql.MySQLCourseDAO;
import com.example.model.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseCatalogueCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> themes = MySQLCourseDAO.findThemes();
        request.setAttribute("themes", themes);

        String theme = request.getParameter("theme");

//        if(theme != null && !theme.equals("All")) {
//            int page = 1;
//            int recordsPerPage = 5;
//            if(request.getParameter("page") != null)
//                page = Integer.parseInt(request.getParameter("page"));
//
//            List<Course> courses = CourseDAO.findAllCoursesByThemeByPage(theme,(page-1)*recordsPerPage, recordsPerPage);
//            request.setAttribute("courses", courses);
//            request.setAttribute("theme", theme);
//            int noOfRecords = CourseDAO.findNumberOfRecordsByTheme(theme);
//            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//
//            request.setAttribute("courses", courses);
//            request.setAttribute("noOfPages", noOfPages);
//            request.setAttribute("currentPage", page);
//
//            request.getRequestDispatcher("/courseCatalogue.jsp").forward(request, response);
//        }

        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<Course> courses = MySQLCourseDAO.findAllCoursesByPage((page-1)*recordsPerPage, recordsPerPage);
        request.setAttribute("courses", courses);
        request.setAttribute("theme", theme);
        int noOfRecords = MySQLCourseDAO.findNumberOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("courses", courses);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return "courseCatalogue.jsp";

//        if (request.getParameter("sort") != null && request.getParameter("sort").equals("a-z")) {
//            courses.sort(Comparator.comparing(Course::getName));
//        }
//        if (request.getParameter("sort") != null  && request.getParameter("sort").equals("z-a")) {
//            courses.sort(Comparator.comparing(Course::getName).reversed());
//        }
//        if (request.getParameter("sort") != null  && request.getParameter("sort").equals("duration")) {
//            courses.sort(Comparator.comparing(Course::getDurationInDays));
//        }
    }
}
