package com.example.controller.command.impl;

import com.example.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.model.constants.Pages.PAGE_NOT_FOUND_PAGE;

/**
 * Uknown command
 */
public class UnknownCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PAGE_NOT_FOUND_PAGE;
    }
}
