package com.example.controller.command.impl;

import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.utils.Pdf;
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

import static com.example.model.constants.Pages.USER_CATALOGUE_PAGE;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class PdfReportCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @Mock
    private Pdf pdf;

    @InjectMocks
    private PdfReportCommand pdfReportCommand;

    @Test
    public void executeShouldReturnUserCataloguePage() {
        final String actual;
        try {
            actual = pdfReportCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }

        MatcherAssert.assertThat(actual, is(USER_CATALOGUE_PAGE));
    }
}
