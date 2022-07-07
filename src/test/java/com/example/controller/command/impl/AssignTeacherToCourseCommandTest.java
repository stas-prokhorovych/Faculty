package com.example.controller.command.impl;

import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.constants.Pages.ADD_TEACHER_PAGE;
import static com.example.model.constants.Pages.HOME_PAGE;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AssignTeacherToCourseCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private AssignTeacherToCourseCommand assignTeacherToCourseCommand;

    @Test
    public void executeShouldReturnRedirectToHomePage() {
        final String actual;
        try {
            actual = assignTeacherToCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(REDIRECT + HOME_PAGE));
    }
}
