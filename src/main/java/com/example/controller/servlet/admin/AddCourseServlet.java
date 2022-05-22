package com.example.controller.servlet.admin;

import com.example.model.dao.CourseDAO;
import com.example.model.entity.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddCourseServlet", value = "/add-course")
public class AddCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/courses").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String theme = request.getParameter("theme");
        String durationInDays = request.getParameter("duration-in-days");
        String idLecturer = request.getParameter("id-lecturer");

        Course course = new Course();
        course.setId(0);
        course.setName(name);
        course.setTheme(theme);
        course.setDurationInDays(Integer.parseInt(durationInDays));
        course.setLecturer(Integer.parseInt(idLecturer));

        CourseDAO.addCourse(course);

        doGet(request, response);
    }
}
