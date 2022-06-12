package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.dao.mysql.MySQLUserDAO;
import com.example.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String phone = request.getParameter("phone");
        String userAccess = request.getParameter("user-access");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(User.Role.STUDENT);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phone);
        user.setUserAccess(Boolean.parseBoolean(userAccess));

        MySQLUserDAO.addUser(user);

        return "profile.jsp";
    }
}
