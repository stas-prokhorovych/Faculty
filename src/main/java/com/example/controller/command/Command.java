package com.example.controller.command;

import com.example.model.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Services a concrete request.
 * Main interface for the Command pattern implementation
 */
public interface Command {
    /**
     * Execution method for command.
     *
     * @param request client request
     * @param response server response
     * @return Address to go once the command is executed.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;
}
