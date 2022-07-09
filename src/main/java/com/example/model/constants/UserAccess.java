package com.example.model.constants;

import java.util.ArrayList;
import java.util.List;

import static com.example.model.constants.Pages.*;

/**
 * User access
 * used by SecurityFilter
 * {@link com.example.controller.filter.SecurityFilter}
 */
public class UserAccess {
    private UserAccess() {
    }

    private static final List<String> guestUrls = new ArrayList<>();
    private static final List<String> studentUrls = new ArrayList<>();
    private static final List<String> teacherUrls = new ArrayList<>();
    private static final List<String> adminUrls = new ArrayList<>();

    static {
        // guest jsp pages and commands
        guestUrls.add(HOME_PAGE);
        guestUrls.add("/css/styling.css");
        guestUrls.add("/js/captchaCheck.js");
        guestUrls.add("/images/favicon.ico");
        guestUrls.add("command=UNKNOWN");
        guestUrls.add("command=LOGIN");
        guestUrls.add("command=REGISTER");
        guestUrls.add("command=COURSE_CATALOGUE");
        guestUrls.add("command=GOTO_HOME_PAGE");
        guestUrls.add("command=GOTO_SIGNUP_PAGE");
        guestUrls.add("command=GOTO_LOGIN_PAGE");

        // student commands
        studentUrls.addAll(getGuestUrls());
        studentUrls.add("command=GOTO_PROFILE_PAGE");
        studentUrls.add("command=LOGOUT");
        studentUrls.add("command=ENROLL_ON_COURSE");
        studentUrls.add("command=LEAVE_COURSE");
        studentUrls.add("command=YOUR_COURSES");


        // teacher commands
        teacherUrls.addAll(getGuestUrls());
        teacherUrls.add("command=GOTO_PROFILE_PAGE");
        teacherUrls.add("command=LOGOUT");
        teacherUrls.add("command=SHOW_JOURNAL");
        teacherUrls.add("command=SHOW_GRADUATES");
        teacherUrls.add("command=END_COURSE");
        teacherUrls.add("command=START_COURSE");
        teacherUrls.add("command=GOTO_GRADUATES_PAGE");

        // admin jsp pages
        adminUrls.addAll(getGuestUrls());
        adminUrls.add("command=GOTO_PROFILE_PAGE");
        adminUrls.add("command=LOGOUT");
        adminUrls.add("command=CREATE_COURSE");
        adminUrls.add("command=DELETE_COURSE");
        adminUrls.add("command=UPDATE_COURSE");
        adminUrls.add("command=USER_CATALOGUE");
        adminUrls.add("command=ADD_TEACHER");
        adminUrls.add("command=PDF_REPORT");
        adminUrls.add("command=BLOCK_USER");
        adminUrls.add("command=UNBLOCK_USER");
        adminUrls.add("command=SHOW_TEACHERS");
        adminUrls.add("command=CREATE_TEACHER");
        adminUrls.add("command=ASSIGN_TEACHER_TO_COURSE");
        adminUrls.add("command=SHOW_COURSE_INFO");
        adminUrls.add("command=GOTO_UPDATE_COURSE_PAGE");
    }

    public static List<String> getGuestUrls() {
        return guestUrls;
    }

    public static List<String> getStudentUrls() {
        return studentUrls;
    }

    public static List<String> getTeacherUrls() {
        return teacherUrls;
    }

    public static List<String> getAdminUrls() {
        return adminUrls;
    }
}
