package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.controller.listener.ConnectionPoolListener;
import com.example.model.service.CourseService;
import com.example.model.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCourseCommand implements Command {

    private static ServiceFactory serviceFactory;
    private static CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        courseService.deleteCourse(Integer.parseInt(id));
        return new CourseCatalogueCommand().execute(request, response);
    }
}
