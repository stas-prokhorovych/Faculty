//package com.example.controller.command.impl;
//
//import com.example.model.service.UserService;
//import com.example.model.service.exception.ServiceException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@RunWith(MockitoJUnitRunner.class)
//public class LoginCommandTest {
//    @Mock
//    private HttpServletRequest request;
//    @Mock
//    private HttpServletResponse response;
//    @Mock
//    private HttpSession session;
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    LoginCommand loginCommand;
//
//    @Test
//    public void executeShouldReturnLoginPageWhenDataIsNotValid() {
//        try {
//            final String actual = loginCommand.execute(request, response);
//
//
//        } catch (ServletException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ServiceException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
