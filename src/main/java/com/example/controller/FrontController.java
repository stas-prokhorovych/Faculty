package com.example.controller;

import com.example.controller.command.Command;
import com.example.controller.command.CommandFactory;
import com.example.model.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.ERROR_PAGE;

/**
 * Front-controller of the whole web-application.
 */
@WebServlet(name = "FrontController", value = "/controller")
public class FrontController extends HttpServlet {

    /**
     * Services a GET-requests.
     *
     * @param request a request object
     * @param response a response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    /**
     * Services a POST-requests.
     *
     * @param request a request object
     * @param response a response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    /**
     * Services requests.
     * @param request a request object
     * @param response a response object
     * @throws ServletException
     * @throws IOException
     */
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getCommand(request);
        String page = null;
        try {
            page = command.execute(request, response);
        } catch (ServiceException e) {
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}
