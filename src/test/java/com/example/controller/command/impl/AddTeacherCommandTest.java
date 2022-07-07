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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AddTeacherCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private AddTeacherCommand addTeacherCommand;

    @Test
    public void executeShouldReturnAddTeacherPage() {
        final String actual;
        try {
            actual = addTeacherCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(ADD_TEACHER_PAGE));
    }
}
