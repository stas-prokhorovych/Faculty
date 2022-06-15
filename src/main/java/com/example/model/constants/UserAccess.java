package com.example.model.constants;

import java.util.ArrayList;
import java.util.List;

import static com.example.model.constants.Paths.*;

public class UserAccess {

    private static List<String> commonUrls = new ArrayList<>();
    private static List<String> studentUrls = new ArrayList<>();
    private static List<String> teacherUrls = new ArrayList<>();
    private static List<String> adminUrls = new ArrayList<>();

    static {
        commonUrls.add(COURSE_CATALOGUE_PATH);
        commonUrls.add(LOGIN_PATH);
        commonUrls.add(REGISTER_PATH);

        adminUrls.add(USER_CATALOGUE_PATH);
    }

    public static List<String> getCommonUrls() {
        return commonUrls;
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
