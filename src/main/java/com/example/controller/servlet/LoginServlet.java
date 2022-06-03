package com.example.controller.servlet;

import com.example.model.dao.UserDAO;
import com.example.model.entity.User;
import com.example.model.security.Security;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(!Security.isLoginValid(login)) {
            request.setAttribute("error", "login not valid");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        if(!Security.isPasswordValid(password)) {
            request.setAttribute("error", "password not valid");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        User user = UserDAO.findUserByLogin(login);
        if(user == null) {
            request.setAttribute("error", "userNotFound");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        if(!Security.isPasswordCorrect(password, user.getPassword())) {
            request.setAttribute("error", "PasswordNotCorrect");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("login", user.getLogin());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole().toString());
        session.setAttribute("name", user.getFirstName());
        session.setAttribute("surname", user.getLastName());
        session.setAttribute("phone", user.getPhoneNumber());

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
