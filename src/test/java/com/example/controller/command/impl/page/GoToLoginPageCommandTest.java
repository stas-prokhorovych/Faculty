package com.example.controller.command.impl.page;

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
import java.io.IOException;

import static com.example.model.constants.Pages.LOGIN_PAGE;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class GoToLoginPageCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private GoToLoginPageCommand goToLoginPageCommand;

    @Test
    public void executeShouldReturnGraduatePage() {
        final String actual;
        try {
            actual = goToLoginPageCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        MatcherAssert.assertThat(actual, is(LOGIN_PAGE));
    }
}

