package com.example.controller;

import com.example.controller.command.Command;
import com.example.controller.command.factory.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController", value = "/controller")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = handleRequest(request, response);
        request.getRequestDispatcher(forward + ".jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = handleRequest(request, response);
        response.sendRedirect(redirect);
    }

    private String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command iCommand = CommandFactory.getCommand(request);
        return iCommand.execute(request, response);
    }
}
