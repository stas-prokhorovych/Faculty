package com.example.controller.command.impl;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.model.constants.Pages.STUDENT_COURSES;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentCoursesCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private CourseService courseService;

    @Mock
    private JournalService journalService;

    @InjectMocks
    private StudentCoursesCommand studentCourses;

    @Test
    public void executionMustReturnStudentCoursesPage() {
        when(request.getSession(false)).thenReturn(session);

        final String actual;
        try {
            actual = studentCourses.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            throw new RuntimeException(e);
        }
        assertThat(actual, is(STUDENT_COURSES));
    }
}
