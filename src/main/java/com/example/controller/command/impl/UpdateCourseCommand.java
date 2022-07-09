package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.service.CourseService;
import com.example.model.service.exception.CourseServiceException;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.FormValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static com.example.model.constants.Pages.PROFILE_PAGE;
import static com.example.model.constants.Pages.UPDATE_COURSE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Update course command
 */
public class UpdateCourseCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UpdateCourseCommand.class);

    private  CourseService courseService;

    public UpdateCourseCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        final String courseId = request.getParameter("id");
        final String name = request.getParameter("name");
        final String theme = request.getParameter("theme");
        final String startDate = request.getParameter("start-date");
        final String endDate = request.getParameter("end-date");
        final String idLecturer = request.getParameter("id-lecturer");
        final String previousName = request.getParameter("prevCourseName");

        Course chosenToUpdateCourse = courseService.findCourseById(courseId);
        request.setAttribute("course", chosenToUpdateCourse);

        Map<String, String> inputErrors = FormValidator.checkAddCourseForm(name, theme, startDate, endDate);
        if(!inputErrors.isEmpty()) {
            for ( Map.Entry<String, String> entry : inputErrors.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            LOG.trace("Update course input errors");
            return UPDATE_COURSE_PAGE;
        }

        request.setAttribute("validName", name);
        request.setAttribute("validTheme", theme);
        request.setAttribute("validStartDate", startDate);
        request.setAttribute("validEndDate", endDate);

        int lecturer;
        if(idLecturer.equals("")) {
            lecturer = chosenToUpdateCourse.getLecturer();
        } else {
            lecturer = Integer.parseInt(idLecturer);
        }

        Course course = new Course.Builder()
                .setId(Integer.parseInt(courseId))
                .setName(name)
                .setTheme(theme)
                .setStartDate(LocalDateTime.parse(startDate))
                .setEndDate(LocalDateTime.parse(endDate))
                .setLecturer(lecturer)
                .setCourseStatus(chosenToUpdateCourse.getCourseStatus())
                .build();

        try {
            courseService.updateCourse(course,previousName);
        } catch (CourseServiceException e) {
            LOG.trace("Update course data errors", e);
            request.setAttribute("dataError", e.getMessage());
            return UPDATE_COURSE_PAGE;
        }

        return REDIRECT + PROFILE_PAGE;
    }
}
