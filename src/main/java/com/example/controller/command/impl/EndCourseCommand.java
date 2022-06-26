package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.JournalService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EndCourseCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final JournalService journalService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        journalService = serviceFactory.getJournalService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String courseId = request.getParameter("courseId");
        String[] studentIds = request.getParameterValues("student-id");
        String[] studentMarks = request.getParameterValues("mark");


        journalService.endCourse(courseId, studentIds, studentMarks);

        return new ShowJournalCommand().execute(request, response);
    }
}
