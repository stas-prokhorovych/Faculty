package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.User;
import com.example.model.exception.ServiceException;
import com.example.model.exception.ServiceWrongLoginException;
import com.example.model.exception.ServiceWrongPasswordException;
import com.example.model.service.UserService;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.service.mysql.MySqlUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.model.constants.Pages.LOGIN_PAGE;
import static com.example.model.constants.Pages.PROFILE_PAGE;

public class LoginCommand implements Command {
    private static ServiceFactory serviceFactory;
    private static UserService userService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");



        User user;
        try {
            user = userService.getUser(login, password);
        } catch (ServiceWrongLoginException e) {
            request.setAttribute("loginError", e.getMessage());
            return LOGIN_PAGE;
        } catch (ServiceWrongPasswordException e) {
            request.setAttribute("passwordError", e.getMessage());
            return LOGIN_PAGE;
        } catch (ServiceException e){
            request.setAttribute("common-error", e.getMessage());
            return LOGIN_PAGE;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("id", user.getId());
        session.setAttribute("login", user.getLogin());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole().toString());
        session.setAttribute("name", user.getFirstName());
        session.setAttribute("surname", user.getLastName());
        session.setAttribute("phone", user.getPhoneNumber());

        return PROFILE_PAGE;
    }
}
