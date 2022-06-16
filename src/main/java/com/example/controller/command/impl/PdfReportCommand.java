package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.utils.Pdf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.model.constants.Pages.USER_CATALOGUE_PAGE;

public class PdfReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:mm:ss");
        String currentDateTime = dateFormatter.format((new Date()));

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        Pdf.export(response);

        return USER_CATALOGUE_PAGE;
    }
}
