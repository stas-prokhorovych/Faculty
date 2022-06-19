package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.User;
import com.example.model.exception.UserServiceException;
import com.example.model.service.UserService;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static com.example.model.constants.Pages.*;

public class RegistrationCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final UserService userService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("password-repeat");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String phone = request.getParameter("phone");

        Map<String, String> inputErrors = Validator.checkSignupForm(login, password, passwordRepeat, email, firstName, lastName, phone);
        if(!inputErrors.isEmpty()) {
            for ( Map.Entry<String, String> entry : inputErrors.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            return SIGNUP_PAGE;
        }

        request.setAttribute("validLogin", login);
        request.setAttribute("validPassword", password);
        request.setAttribute("validPasswordRepeat", passwordRepeat);
        request.setAttribute("validEmail", email);
        request.setAttribute("validFirstName", firstName);
        request.setAttribute("validLastName", lastName);
        request.setAttribute("validPhone", phone);





        try {
            userService.findUserByLogin(login);
        } catch (UserServiceException e){
            request.setAttribute("dataError", e.getMessage());
            return SIGNUP_PAGE;
        }

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(User.Role.STUDENT);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phone);
        user.setUserAccess(true);

        userService.addUser(user);

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
