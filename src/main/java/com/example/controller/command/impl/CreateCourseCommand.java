package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.exception.CourseServiceException;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.utils.FormValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.example.model.constants.Pages.ADD_COURSE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;

/**
 * Course creation command
 */
public class CreateCourseCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CreateCourseCommand.class);

    private CourseService courseService;
    private UserService userService;

    public CreateCourseCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession(true);

        if(session.getAttribute("successHintShow") != null && session.getAttribute("successHintShow").equals("1")) {
            session.removeAttribute("successHintShow");
            session.removeAttribute("successCourseCreation");
        }

        if(session.getAttribute("successCourseCreation") != null) {
            session.setAttribute("successHintShow", "1");
            LOG.trace("Showing success hint");

            return ADD_COURSE_PAGE;
        }

        String name = request.getParameter("name");
        String theme = request.getParameter("theme");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        String idLecturer = request.getParameter("id-lecturer");

        Map<String, String> inputErrors = FormValidator.checkAddCourseForm(name, theme, startDate, endDate);
        if (!inputErrors.isEmpty()) {
            for (Map.Entry<String, String> entry : inputErrors.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            List<User> teacherForForm = userService.findByRole("Teacher");
            request.setAttribute("teacherForForm", teacherForForm);
            LOG.debug("Input errors");
            return ADD_COURSE_PAGE;
        }

        request.setAttribute("validName", name);
        request.setAttribute("validTheme", theme);
        request.setAttribute("validStartDate", startDate);
        request.setAttribute("validEndDate", endDate);

        int lecturer;
        Course.CourseStatus status;
        if (idLecturer == null || idLecturer.equals("")) {
            lecturer = 0;
            status = Course.CourseStatus.CLOSED;
        } else {
            lecturer = Integer.parseInt(idLecturer);
            status = Course.CourseStatus.OPENED;
        }

        Course course = new Course.Builder()
                .setName(name)
                .setTheme(theme)
                .setStartDate(LocalDateTime.parse(startDate))
                .setEndDate(LocalDateTime.parse(endDate))
                .setLecturer(lecturer)
                .setCourseStatus(status)
                .build();

        try {
            courseService.addCourse(course);
        } catch (CourseServiceException e) {
            request.setAttribute("dataError", e.getMessage());
            LOG.debug("Data error: ", e);
            List<User> teacherForForm = userService.findByRole("Teacher");
            request.setAttribute("teacherForForm", teacherForForm);
            return ADD_COURSE_PAGE;
        }

        session.setAttribute("successCourseCreation", "Course was created");
        session.setAttribute("successHintShow", "0");
        return REDIRECT + ADD_COURSE_PAGE;
    }
}
