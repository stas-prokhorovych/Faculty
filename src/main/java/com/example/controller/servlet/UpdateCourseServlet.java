package com.example.controller.servlet;

import com.example.model.dao.CourseDAO;
import com.example.model.entity.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateCourseServlet", value = "/update-course")
public class UpdateCourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        CourseDAO.updateCourse(name, Integer.parseInt(id));
        req.getRequestDispatcher("/courses").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = CourseDAO.findName(Integer.parseInt(id));

        Course course = new Course();

        course.setId(Integer.parseInt(id));
        course.setName(name);
        request.setAttribute("course", course);

        request.getRequestDispatcher("/update.jsp").forward(request, response);

    }
}
