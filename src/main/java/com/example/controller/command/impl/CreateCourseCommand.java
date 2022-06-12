package com.example.controller.command.impl;

import com.example.controller.command.Command;
import com.example.model.dao.mysql.MySQLCourseDAO;
import com.example.model.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String theme = request.getParameter("theme");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        String idLecturer = request.getParameter("id-lecturer");

        Course course = new Course();
        course.setId(0);
        course.setName(name);
        course.setTheme(theme);
        course.setStartDate(LocalDateTime.parse(startDate));
        course.setEndDate(LocalDateTime.parse(endDate));
        course.setLecturer(Integer.parseInt(idLecturer));
        course.setCourseStatus(Course.CourseStatus.CLOSED);

        MySQLCourseDAO.addCourse(course);

        return "courses.jsp";
    }
}
