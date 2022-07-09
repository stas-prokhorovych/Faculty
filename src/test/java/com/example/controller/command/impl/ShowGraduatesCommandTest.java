package com.example.controller.command.impl;

import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
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

import static com.example.model.constants.Pages.GRADUATES_PAGE;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowGraduatesCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private ShowGraduatesCommand showGraduatesCommand;

    @Test
    public void executionShouldReturnRedirectToGraduatesPage() {
        when(request.getSession(true)).thenReturn(session);

        String expected = REDIRECT + GRADUATES_PAGE;
        final String actual;
        try {
            actual = showGraduatesCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }

        MatcherAssert.assertThat(actual, is(expected));
    }
}
