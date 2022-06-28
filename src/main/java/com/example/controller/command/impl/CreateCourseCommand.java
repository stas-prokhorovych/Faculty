package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.service.exception.CourseServiceException;
import com.example.model.service.CourseService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static com.example.model.constants.Pages.*;

public class CreateCourseCommand implements Command {
    private static final ServiceFactory serviceFactory;
    private static final CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String name = request.getParameter("name");
        String theme = request.getParameter("theme");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        String idLecturer = request.getParameter("id-lecturer");

        Map<String, String> inputErrors = Validator.checkAddCourseForm(name, theme, startDate, endDate);
        if(!inputErrors.isEmpty()) {
            for ( Map.Entry<String, String> entry : inputErrors.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            return new ShowTeachersCommand().execute(request, response);
        }

        request.setAttribute("validName", name);
        request.setAttribute("validTheme", theme);
        request.setAttribute("validStartDate", startDate);
        request.setAttribute("validEndDate", endDate);

        Course course = new Course();
        course.setName(name);
        course.setTheme(theme);
        course.setStartDate(LocalDateTime.parse(startDate));
        course.setEndDate(LocalDateTime.parse(endDate));

        if(idLecturer == null || idLecturer.equals("")) {
            course.setLecturer(0);
            course.setCourseStatus(Course.CourseStatus.CLOSED);
        } else {
            course.setLecturer(Integer.parseInt(idLecturer));
            course.setCourseStatus(Course.CourseStatus.OPENED);
        }
        try {
            courseService.addCourse(course);
        } catch (CourseServiceException e) {
            request.setAttribute("dataError", e.getMessage());
            return new ShowTeachersCommand().execute(request, response);
        }

        return PROFILE_PAGE;
    }
}
