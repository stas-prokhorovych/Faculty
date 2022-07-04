package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.USER_CATALOGUE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Unblock user command
 */
public class UnblockUserCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final UserService userService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String studentId = request.getParameter("id");
        userService.updateUserAccess(true, studentId);
        return REDIRECT + USER_CATALOGUE_PAGE;
    }
}
