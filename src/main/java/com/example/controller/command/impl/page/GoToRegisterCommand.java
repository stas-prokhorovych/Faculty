package com.example.controller.command.impl.page;

import com.example.controller.command.Command;
import com.example.model.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.SIGNUP_PAGE;

/**
 * Returns register page
 */
public class GoToRegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        return SIGNUP_PAGE;
    }
}
