package com.example.controller;

import com.example.controller.command.Command;
import com.example.controller.command.CommandFactory;
import com.example.model.constants.Prg;
import com.example.model.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.example.model.constants.Pages.ERROR_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Front-controller of the whole web-application.
 */
@WebServlet(name = "FrontController", value = "/controller")
public class FrontController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(FrontController.class);

    /**
     * Services a GET-requests.
     *
     * @param request  a request object
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
     * @param request  a request object
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
     *
     * @param request  a request object
     * @param response a response object
     * @throws ServletException
     * @throws IOException
     */
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getCommand(request);
        String page = null;
        try {
            page = command.execute(request, response);
            if(page.contains(REDIRECT)) {
                Map<String, String> redirectPath = Prg.getRedirectPath();
                LOG.trace("Redirect to address = " + page);
                response.sendRedirect(redirectPath.get(page));
                return;
            }
        } catch (ServiceException e) {
            LOG.error("Cannot execute command: " + e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }

        LOG.trace("Forward to address = " + page);
        request.getRequestDispatcher(page).forward(request, response);
    }
}