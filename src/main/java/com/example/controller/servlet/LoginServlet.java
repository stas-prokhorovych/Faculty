package com.example.controller.servlet;

import com.example.controller.Constants;
import com.example.model.dao.UserDAO;
import com.example.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = UserDAO.findUserByLogin(login);
        if(user == null) {
            request.setAttribute("error", Constants.USER_NOT_FOUND);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        request.getRequestDispatcher("success.jsp").forward(request, response);
    }
}
