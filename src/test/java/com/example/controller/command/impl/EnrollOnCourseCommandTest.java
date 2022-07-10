package com.example.controller.command.impl;

import com.example.model.service.UserService;
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

import static com.example.model.constants.Pages.COURSE_CATALOGUE_PAGE;
import static com.example.model.constants.Pages.LOGIN_PAGE;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnrollOnCourseCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private EnrollOnCourseCommand enrollOnCourseCommand;

    @Test
    public void executeShouldReturnRedirectToCourseCataloguePage() {
        when(request.getParameter(anyString())).thenReturn("1");
        String actual;
        try {
            actual = enrollOnCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(REDIRECT + COURSE_CATALOGUE_PAGE));
    }
}
