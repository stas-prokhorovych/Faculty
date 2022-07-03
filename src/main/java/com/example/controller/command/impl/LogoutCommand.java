package com.example.controller.command.impl;

import com.example.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.model.constants.Pages.HOME_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        session.invalidate();

        return REDIRECT + HOME_PAGE;
    }
}
