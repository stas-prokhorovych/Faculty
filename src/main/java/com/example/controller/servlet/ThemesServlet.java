package com.example.controller.servlet;

import com.example.model.dao.CourseDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindCoursesServlet", value = "/find-themes")
public class ThemesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> themes = CourseDAO.findThemes();
        request.setAttribute("themes", themes);
        request.getRequestDispatcher("/themes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
