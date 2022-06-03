package com.example.controller.servlet;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.UserDAO;
import com.example.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeacherCoursesServlet", value = "/find-teachers")
public class TeacherCoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> teachers = UserDAO.findTeachers();
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("/teacher.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
