package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.User;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.FormValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static com.example.model.constants.Pages.LOGIN_PAGE;
import static com.example.model.constants.Pages.PROFILE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

public class LoginCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final UserService userService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Map<String, String> inputErrors = FormValidator.checkLoginForm(login, password);
        if(!inputErrors.isEmpty()) {
            for ( Map.Entry<String, String> entry : inputErrors.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            return LOGIN_PAGE;
        }

        request.setAttribute("validLogin", login);

        User user;
        try {
            user = userService.getUser(login, password);
        } catch (UserServiceException e){
            request.setAttribute("dataError", e.getMessage());
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
        session.setAttribute("access", user.isUserAccess());
        return REDIRECT + PROFILE_PAGE;
    }
}
