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


        guestUrls.add(COURSE_CATALOGUE_PATH);
        guestUrls.add(LOGIN_PATH);
        guestUrls.add(REGISTER_PATH);
        guestUrls.add(HOME_PAGE);

        adminUrls.add(USER_CATALOGUE_PATH);
    }

    public static List<String> getGuestUrls() {
        return guestUrls;
    }

    public static void setGuestUrls(List<String> guestUrls) {
        UserAccess.guestUrls = guestUrls;
    }

    public static List<String> getStudentUrls() {
        return studentUrls;
    }

    public static void setStudentUrls(List<String> studentUrls) {
        UserAccess.studentUrls = studentUrls;
    }

    public static List<String> getTeacherUrls() {
        return teacherUrls;
    }

    public static void setTeacherUrls(List<String> teacherUrls) {
        UserAccess.teacherUrls = teacherUrls;
    }

    public static List<String> getAdminUrls() {
        return adminUrls;
    }

    public static void setAdminUrls(List<String> adminUrls) {
        UserAccess.adminUrls = adminUrls;
    }
}
