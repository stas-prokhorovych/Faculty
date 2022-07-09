package com.example.controller.command.impl;

import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.exception.CourseServiceException;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.example.model.constants.Pages.*;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateCourseCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private UpdateCourseCommand updateCourseCommand;

    @Test
    public void executeShouldReturnUpdateCoursePageWhenFormValidationErrors() {
        final String actual;
        try {
            actual = updateCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(UPDATE_COURSE_PAGE));
    }

    @Test
    public void executeMustReturnUpdateCoursePageWhenDataErrors() {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Java");
        when(request.getParameter("theme")).thenReturn("Computer Science");
        when(request.getParameter("start-date")).thenReturn("2022-07-04T17:38");
        when(request.getParameter("end-date")).thenReturn("2022-07-04T17:40");
        when(request.getParameter("id-lecturer")).thenReturn("");
        when(request.getParameter("prevCourseName")).thenReturn("SQL");

        Course course = new Course.Builder()
                .setId(1)
                .setName("name")
                .setTheme("Theme")
                .setStartDate(LocalDateTime.parse("2022-07-04T17:38"))
                .setEndDate(LocalDateTime.parse("2022-07-04T17:38"))
                .setLecturer(1)
                .setCourseStatus(Course.CourseStatus.OPENED)
                .build();

        try {
            when(courseService.findCourseById(anyString())).thenReturn(course);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        String actual;
        try {
            doThrow(new CourseServiceException()).when(courseService).updateCourse(any(Course.class), anyString());
            actual = updateCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(UPDATE_COURSE_PAGE));
    }

    @Test
    public void executeShouldReturnRedirectToUpdateCoursePageWhenSuccess() {
        Course course = new Course.Builder()
                .setId(1)
                .setName("name")
                .setTheme("Theme")
                .setStartDate(LocalDateTime.parse("2022-07-04T17:38"))
                .setEndDate(LocalDateTime.parse("2022-07-04T17:38"))
                .setLecturer(1)
                .setCourseStatus(Course.CourseStatus.OPENED)
                .build();


        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Java");
        when(request.getParameter("theme")).thenReturn("Computer Science");
        when(request.getParameter("start-date")).thenReturn("2022-07-04T17:38");
        when(request.getParameter("end-date")).thenReturn("2022-07-04T17:40");
        when(request.getParameter("id-lecturer")).thenReturn("3");

        String expected = REDIRECT + PROFILE_PAGE;
        String actual;
        try {
            when(courseService.findCourseById(anyString())).thenReturn(course);
            actual = updateCourseCommand.execute(request, response);
        } catch (ServletException | ServiceException | IOException e) {
            throw new RuntimeException(e);
        }
        MatcherAssert.assertThat(actual, is(expected));
    }
}
