package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.dao.mysql.MySQLCourseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        MySQLCourseDAO.deleteCourse(Integer.parseInt(id));
        return "courseCatalogue.jsp";
    }
}
