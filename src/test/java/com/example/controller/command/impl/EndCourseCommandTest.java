package com.example.controller.command.impl;

import com.example.model.entity.Journal;
import com.example.model.service.CourseService;
import com.example.model.service.JournalService;
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

import static com.example.model.constants.Pages.LOGIN_PAGE;
import static com.example.model.constants.Pages.SHOW_JOURNAL_PAGE;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EndCourseCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private JournalService journalService;

    @InjectMocks
    private EndCourseCommand endCourseCommand;

    @Test
    public void executeWhenNoParameterShouldReturnRedirectToShowJournalPage() {
        when(request.getParameter(anyString())).thenReturn("");
        when(request.getParameterValues(anyString())).thenReturn(new String[0]);

        final String actual;
        try {
            actual = endCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(REDIRECT + SHOW_JOURNAL_PAGE));
    }

    @Test
    public void executeShouldReturnRedirectToShowJournalPageWhenSuccess() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getParameterValues(anyString())).thenReturn(new String[]{"1", "2"});

        final String actual;
        try {
            actual = endCourseCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(REDIRECT + SHOW_JOURNAL_PAGE));
    }
}
