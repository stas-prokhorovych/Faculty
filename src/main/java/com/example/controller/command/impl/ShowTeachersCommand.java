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

import static com.example.model.constants.Pages.ADD_COURSE_PAGE;

/**
 * Show teachers command
 */
public class ShowTeachersCommand implements Command {
    private UserService userService;

    public ShowTeachersCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        List<User> teacherForForm = userService.findByRole("Teacher");
        request.setAttribute("teacherForForm", teacherForForm);

        return ADD_COURSE_PAGE;
    }
}
