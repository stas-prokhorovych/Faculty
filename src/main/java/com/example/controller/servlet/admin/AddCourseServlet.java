package com.example.controller.servlet.admin;

import com.example.model.dao.CourseDAO;
import com.example.model.entity.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AddCourseServlet", value = "/add-course")
public class AddCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/courses").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
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

        CourseDAO.addCourse(course);

        doGet(request, response);
    }
}
