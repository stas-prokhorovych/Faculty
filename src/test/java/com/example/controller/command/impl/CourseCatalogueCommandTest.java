package com.example.controller.command.impl;

import com.example.model.entity.Course;
import com.example.model.service.CourseService;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.utils.CourseCatalogueInfo;
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
import java.util.Collections;
import java.util.List;

import static com.example.model.constants.Pages.COURSE_CATALOGUE_PAGE;
import static com.example.model.constants.Pages.USER_CATALOGUE_PAGE;
import static com.example.model.constants.Prg.REDIRECT;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseCatalogueCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Mock
    private HttpSession session;

    @Mock
    private CourseCatalogueInfo catalogueInfo = new CourseCatalogueInfo();

    @InjectMocks
    private CourseCatalogueCommand courseCatalogueCommand;

    @Test
    public void executeShouldReturnCourseCataloguePage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn("attr");


        when(catalogueInfo.getCourses()).thenReturn(Collections.emptyList());
        when(catalogueInfo.getTeachers()).thenReturn(Collections.emptyList());
        when(catalogueInfo.getStudentsEnrolled()).thenReturn(Collections.emptyList());

        catalogueInfo.setCourses(Collections.emptyList());
        catalogueInfo.setTeachers(Collections.emptyList());
        catalogueInfo.setStudentsEnrolled(Collections.emptyList());

        try {
            when(courseService.findCoursesByPage(anyInt(), anyInt(), anyString(), anyString(), anyInt(), anyString(), anyString())).thenReturn(catalogueInfo);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


        String actual;
        try {
            actual = courseCatalogueCommand.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        MatcherAssert.assertThat(actual, is(COURSE_CATALOGUE_PAGE));
    }
}
