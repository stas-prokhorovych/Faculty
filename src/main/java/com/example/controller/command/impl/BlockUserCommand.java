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
 * Block user command
 */
public class BlockUserCommand implements Command {
    private UserService userService;

    public BlockUserCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String studentId = request.getParameter("id");
        userService.updateUserAccess(false, studentId);
        return REDIRECT + USER_CATALOGUE_PAGE;
    }
}
