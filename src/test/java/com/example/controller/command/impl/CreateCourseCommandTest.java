package com.example.controller.command.impl;

import com.example.model.entity.Course;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.exception.CourseServiceException;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
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

import static com.example.model.constants.Pages.ADD_COURSE_PAGE;
import static com.example.model.constants.Pages.LOGIN_PAGE;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateCourseCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private CourseService courseService;

    @Mock
    private HttpSession session;

    @InjectMocks
    private CreateCourseCommand createCourseCommand;

    @Test
    public void executeShouldReturnAddCoursePageWhenValidationErrors() {
        when(request.getSession(true)).thenReturn(session);

        when(request.getParameter(anyString())).thenReturn("p");
        String actual;
        try {
            actual = createCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(ADD_COURSE_PAGE));
    }

    @Test
    public void executeShouldReturnAddCoursePageWhenSecondSubmitAfterSuccess() {
        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("successCourseCreation")).thenReturn("something");

        String actual;
        try {
            actual = createCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(ADD_COURSE_PAGE));
    }

    @Test
    public void executeShouldReturnAddCoursePageWhenDataErrors() {
        when(request.getSession(true)).thenReturn(session);

        when(request.getParameter("name")).thenReturn("Java");
        when(request.getParameter("theme")).thenReturn("Computer Science");
        when(request.getParameter("start-date")).thenReturn("2022-07-04T17:38");
        when(request.getParameter("end-date")).thenReturn("2022-07-04T17:40");
        when(request.getParameter("id-lecturer")).thenReturn("");

        String actual;
        try {

            doThrow(new CourseServiceException()).when(courseService).addCourse(any(Course.class));

            actual = createCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(ADD_COURSE_PAGE));
    }

    @Test
    public void executeShouldRedirectToAddCoursePageWhenSuccess() {
        when(request.getSession(true)).thenReturn(session);

        when(request.getParameter("name")).thenReturn("Java");
        when(request.getParameter("theme")).thenReturn("Computer Science");
        when(request.getParameter("start-date")).thenReturn("2022-07-04T17:38");
        when(request.getParameter("end-date")).thenReturn("2022-07-04T17:40");
        when(request.getParameter("id-lecturer")).thenReturn("");

        String actual;
        try {
            actual = createCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(REDIRECT + ADD_COURSE_PAGE));
    }
}
