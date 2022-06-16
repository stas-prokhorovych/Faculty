package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.service.CourseService;
import com.example.model.service.JournalService;
import com.example.model.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.model.constants.Pages.SHOW_JOURNAL_PAGE;

public class ShowJournalCommand implements Command {
    private static ServiceFactory serviceFactory;
    private static JournalService journalService;
    private static CourseService courseService;

    static {
        serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
        courseService = serviceFactory.getCourseService();
        journalService = serviceFactory.getJournalService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer teacherId = (Integer) request.getSession(false).getAttribute("id");

        List<Course> finishedCourses =  courseService.findAllFinishedCoursesByTeacherId(teacherId);
        List<Course> inProgressCourses = courseService.findAllInProgressCoursesByTeacherId(teacherId);
        for(Course course: inProgressCourses) {
            System.out.println(course.getName());
        }


        request.setAttribute("inProgressCourses", inProgressCourses);

//        request.setAttribute("finishedCourses", finishedCourses);
//        request.setAttribute("studentFinishedCourse", studentFinishedCourse);
//        request.setAttribute("marks", marks);

        return SHOW_JOURNAL_PAGE;
    }
}
