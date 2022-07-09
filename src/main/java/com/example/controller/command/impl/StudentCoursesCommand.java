package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.entity.Journal;
import com.example.model.service.CourseService;
import com.example.model.service.JournalService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.model.constants.Pages.STUDENT_COURSES;

/**
 * Student courses command
 */
public class StudentCoursesCommand implements Command {
    private CourseService courseService;
    private JournalService journalService;

    public StudentCoursesCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
        journalService = serviceFactory.getJournalService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        Integer studentId = (Integer) request.getSession(false).getAttribute("id");

        List<Course> registeredCourses = courseService.findStudentCoursesByStatus(studentId, "Opened for registration");
        List<Course> inProgressCourses = courseService.findStudentCoursesByStatus(studentId, "In progress");
        List<Course> finishedCourses = courseService.findStudentCoursesByStatus(studentId, "Finished");
        List<Journal> journalInfo = journalService.findJournalInfo(finishedCourses, studentId);

        request.setAttribute("registeredCourses", registeredCourses);
        request.setAttribute("inProgressCourses", inProgressCourses);
        request.setAttribute("finishedCourses", finishedCourses);
        request.setAttribute("journalInfo", journalInfo);
        return STUDENT_COURSES;
    }
}
