package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.JournalService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.SHOW_JOURNAL_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

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

        if(studentIds == null || studentMarks == null || studentIds.length == 0 || studentMarks.length == 0) {
            journalService.endCourse(courseId);
            return new ShowJournalCommand().execute(request, response);
        }

        journalService.endCourse(courseId, studentIds, studentMarks);

        return REDIRECT + SHOW_JOURNAL_PAGE;
    }
}
