package com.example.controller.command.impl.page;

import com.example.controller.command.Command;
import com.example.model.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.UPDATE_COURSE_PAGE;

/**
 * Returns update course page
 */
public class GoToUpdateCoursePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        return UPDATE_COURSE_PAGE;
    }
}
