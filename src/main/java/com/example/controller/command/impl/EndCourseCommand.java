package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.service.JournalService;
import com.example.model.service.exception.CourseServiceException;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.SHOW_JOURNAL_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Edit course command
 */
public class EndCourseCommand implements Command {
    private JournalService journalService;

    public EndCourseCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        journalService = serviceFactory.getJournalService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String courseId = request.getParameter("courseId");

        String[] studentIds = request.getParameterValues("student-id");
        String[] studentMarks = request.getParameterValues("mark");


        if(studentIds == null || studentMarks == null || studentIds.length == 0 || studentMarks.length == 0) {
            try {
                journalService.endCourse(courseId);
            } catch (CourseServiceException e) {
                request.setAttribute("dataError", e.getMessage());
                return new ShowJournalCommand().execute(request, response);
            }
            return REDIRECT + SHOW_JOURNAL_PAGE;
        }

        try {
            journalService.endCourse(courseId, studentIds, studentMarks);
        } catch (CourseServiceException e) {
            request.setAttribute("dataError", e.getMessage());
            return new ShowJournalCommand().execute(request, response);
        }

        return REDIRECT + SHOW_JOURNAL_PAGE;
    }
}
