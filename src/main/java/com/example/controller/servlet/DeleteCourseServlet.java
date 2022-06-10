package com.example.controller.servlet;

import com.example.model.dao.CourseDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteCourseServlet", value = "/delete-course")
public class DeleteCourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CourseDAO.deleteCourse(Integer.parseInt(id));
        request.getRequestDispatcher("/list").forward(request, response);
    }
}
