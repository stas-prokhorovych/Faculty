package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.User;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.GRADUATES_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Show graduates command
 */
public class ShowGraduatesCommand implements Command {
    private UserService userService;

    public ShowGraduatesCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Integer courseId;
        HttpSession session = request.getSession(true);

        if(request.getParameter("course-id") != null) {
            courseId = Integer.valueOf(request.getParameter("course-id"));
        } else {
            courseId = (Integer) session.getAttribute("prgGraduateCourseId");
        }

        List<User> graduates = userService.findAllGraduates(courseId);

        session.setAttribute("prgGraduates", graduates);
        session.setAttribute("prgGraduateCourseId", courseId);

        return REDIRECT + GRADUATES_PAGE;
    }
}
