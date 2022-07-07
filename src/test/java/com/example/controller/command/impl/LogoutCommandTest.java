package com.example.controller.command.impl;

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

import static com.example.model.constants.Pages.HOME_PAGE;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LogoutCommand logoutCommand;

    @Test
    public void executeShouldReturnRedirectToHomePage() {
        when(request.getSession()).thenReturn(session);

        String expected = REDIRECT + HOME_PAGE;
        final String actual;
        try {
            actual = logoutCommand.execute(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

        verify(session).invalidate();
        MatcherAssert.assertThat(actual, is(expected));
    }
}
