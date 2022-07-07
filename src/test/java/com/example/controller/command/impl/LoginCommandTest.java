package com.example.controller.command.impl;

import com.example.model.entity.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.model.constants.Pages.LOGIN_PAGE;
import static com.example.model.constants.Pages.PROFILE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginCommand loginCommand;

    @Test
    public void executeShouldReturnLoginPageWhenFormValidationErrors() {
        final String actual;
        try {
            actual = loginCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(LOGIN_PAGE));
    }


//    @Test
//    public void executeShouldReturnRedirectPageWhenSuccess() {
//        User user = new User.Builder()
//                .setLogin("Login")
//                .setPassword("password")
//                .setEmail("stas@gmail.com")
//                .setRole(User.Role.STUDENT)
//                .setFirstName("Name")
//                .setLastName("Surname")
//                .setPhoneNumber("0977605026")
//                .setUserAccess(true)
//                .build();
//
//        when(request.getSession()).thenReturn(session);
//        when(request.getParameter(anyString())).thenReturn("param");
//
//
//        try {
//            when(userService.getUser(anyString(), anyString())).thenReturn(user);
//        } catch (ServiceException e) {
//            throw new RuntimeException(e);
//        }
//
//        String expected = REDIRECT + PROFILE_PAGE;
//        String actual;
//        try {
//            actual = loginCommand.execute(request, response);
//        } catch (ServletException | ServiceException | IOException e) {
//            throw new RuntimeException(e);
//        }
//        assertEquals(expected, is(actual));
//    }
}
