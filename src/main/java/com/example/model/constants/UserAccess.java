package com.example.model.constants;

import java.util.ArrayList;
import java.util.List;

import static com.example.model.constants.Pages.*;
import static com.example.model.constants.Paths.*;

public class UserAccess {

    private static List<String> guestUrls = new ArrayList<>();
    private static List<String> studentUrls = new ArrayList<>();
    private static List<String> teacherUrls = new ArrayList<>();
    private static List<String> adminUrls = new ArrayList<>();

    static {
        // guest jsp pages
        guestUrls.add(HOME_PAGE);
        guestUrls.add(LOGIN_PAGE);
        guestUrls.add(SIGNUP_PAGE);
        // student jsp pages
        guestUrls.add(HOME_PAGE);
        // teacher jsp pages


        // admin jsp pages
        adminUrls.add(USER_CATALOGUE_PATH);




        guestUrls.add(LOGIN_PATH);
        guestUrls.add(REGISTER_PATH);
        guestUrls.add(HOME_PAGE);
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
