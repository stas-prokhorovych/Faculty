package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.User;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.USER_CATALOGUE_PAGE;

/**
 * User catalogue command
 */
public class UserCatalogueCommand implements Command {
    private UserService userService;

    public UserCatalogueCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        List<User> students = userService.findByRole("Student");
        List<User> teachers = userService.findByRole("Teacher");

        request.setAttribute("students", students);
        request.setAttribute("teachers", teachers);

        return USER_CATALOGUE_PAGE;
    }
}
