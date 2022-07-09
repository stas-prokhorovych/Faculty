package com.example.controller.command.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.model.constants.Pages.PAGE_NOT_FOUND_PAGE;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class UnknownCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private UnknownCommand unknownCommand;

    @Test
    public void executeShouldReturnPageNotFound() {
        String actual;
        actual = unknownCommand.execute(request, response);
        MatcherAssert.assertThat(actual, is(PAGE_NOT_FOUND_PAGE));
    }
}
