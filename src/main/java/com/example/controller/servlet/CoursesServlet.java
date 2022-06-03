package com.example.controller.servlet;

import com.example.model.dao.CourseDAO;
import com.example.model.entity.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "CoursesServlet", value = "/find-courses")
public class CoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theme = request.getParameter("theme");
        List<Course> courses = CourseDAO.findAllCoursesByTheme(theme);
        if (request.getParameter("sort") != null && request.getParameter("sort").equals("a-z")) {
            courses.sort(Comparator.comparing(Course::getName));
        }
        if (request.getParameter("sort") != null  && request.getParameter("sort").equals("z-a")) {
            courses.sort(Comparator.comparing(Course::getName).reversed());
        }
        if (request.getParameter("sort") != null  && request.getParameter("sort").equals("duration")) {
            courses.sort(Comparator.comparing(Course::getDurationInDays));
        }
        request.setAttribute("courses", courses);
        request.setAttribute("theme", theme);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
