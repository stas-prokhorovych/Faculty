package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.User;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.Pdf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.model.constants.Pages.USER_CATALOGUE_PAGE;

/**
 * Pdf command
 */
public class PdfReportCommand implements Command {
    private UserService userService;
    private Pdf pdf;

    public PdfReportCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        userService = serviceFactory.getUserService();
        pdf = new Pdf();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String role = request.getParameter("role");

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:mm:ss");
        String currentDateTime = dateFormatter.format((new Date()));

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);
        List<User> students = userService.findByRole(role);
        pdf.export(response, students, role);

        return USER_CATALOGUE_PAGE;
    }
}
