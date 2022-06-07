package com.example.controller.servlet;

import com.example.model.dao.CourseDAO;
import com.example.model.entity.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "SomeServlet", value = "/list")
public class CoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> themes = CourseDAO.findThemes();
        request.setAttribute("themes", themes);

        String theme = request.getParameter("theme");
//        List<Course> courses = CourseDAO.findAllCoursesByTheme(theme);
        if(theme!=null && theme.equals("All")) {
            int page = 1;
            int recordsPerPage = 5;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));

            List<Course> courses = CourseDAO.findAllCoursesByPage((page-1)*recordsPerPage, recordsPerPage);
            request.setAttribute("courses", courses);
            request.setAttribute("theme", theme);
            int noOfRecords = CourseDAO.findNumberOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            request.setAttribute("courses", courses);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            request.getRequestDispatcher("/list.jsp").forward(request, response);
        }
        if(theme!=null) {

        }

        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<Course> courses = CourseDAO.findAllCoursesByPage((page-1)*recordsPerPage, recordsPerPage);
        request.setAttribute("courses", courses);
        request.setAttribute("theme", theme);
        int noOfRecords = CourseDAO.findNumberOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("courses", courses);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("/list.jsp").forward(request, response);


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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
