package com.example;

import com.example.controller.command.impl.LoginCommand;
import com.example.controller.command.impl.RegistrationCommand;
import com.example.model.entity.User;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
import com.example.model.utils.PasswordHelper;
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

import static com.example.model.constants.Pages.*;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegistrationCommand registrationCommand;

    @Test
    public void executeMustReturnSignupPageWhenValidationErrors() {
        final String actual;
        try {
            actual = registrationCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(SIGNUP_PAGE));
    }

    @Test
    public void executeMustReturnSignupPageWhenDataErrors() {

        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password-repeat")).thenReturn("password");
        when(request.getParameter("email")).thenReturn("stas@gmail.com");
        when(request.getParameter("first-name")).thenReturn("Stas");
        when(request.getParameter("last-name")).thenReturn("Prokhorovych");
        when(request.getParameter("phone")).thenReturn("0678354430");

        String actual;
        try {
            doThrow(new UserServiceException()).when(userService).findUserByLogin(anyString());
            actual = registrationCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(SIGNUP_PAGE));
    }

    @Test
    public void executeMustRedirectProfilePageWhenSuccess() {
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password-repeat")).thenReturn("password");
        when(request.getParameter("email")).thenReturn("stas@gmail.com");
        when(request.getParameter("first-name")).thenReturn("Stas");
        when(request.getParameter("last-name")).thenReturn("Prokhorovych");
        when(request.getParameter("phone")).thenReturn("0678354430");

        when(request.getSession(true)).thenReturn(session);

        User user = new User.Builder()
                .setLogin("login")
                .setPassword(PasswordHelper.encrypt("password"))
                .setEmail("stas@gmail.com")
                .setRole(User.Role.STUDENT)
                .setFirstName("Name")
                .setLastName("Surname")
                .setPhoneNumber("0977605026")
                .setUserAccess(true)
                .build();


        String actual;
        try {
            when(userService.getUser(anyString(), anyString())).thenReturn(user);
            actual = registrationCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(REDIRECT + PROFILE_PAGE));
    }
}
