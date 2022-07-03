package com.example.model.constants;

import java.util.HashMap;
import java.util.Map;

import static com.example.model.constants.Pages.*;

public class Prg {
    private Prg() {
    }

    private static final Map<String, String> redirectPath = new HashMap<>();

    public static final String REDIRECT = "redirect:";
    private static final String PREFIX = "controller?command=";

    static {
        redirectPath.put(REDIRECT + PROFILE_PAGE, PREFIX + "GOTO_PROFILE_PAGE");
        redirectPath.put(REDIRECT + COURSE_CATALOGUE_PAGE, PREFIX + "COURSE_CATALOGUE");
        redirectPath.put(REDIRECT + SHOW_JOURNAL_PAGE, PREFIX + "SHOW_JOURNAL");
        redirectPath.put(REDIRECT + USER_CATALOGUE_PAGE, PREFIX + "USER_CATALOGUE");
        redirectPath.put(REDIRECT + ADD_COURSE_PAGE, PREFIX + "CREATE_COURSE");
        redirectPath.put(REDIRECT + HOME_PAGE, PREFIX + "GOTO_HOME_PAGE");
        redirectPath.put(REDIRECT + UPDATE_COURSE_PAGE, PREFIX + "GOTO_UPDATE_COURSE_PAGE");
        redirectPath.put(REDIRECT + GRADUATES_PAGE, PREFIX + "GOTO_GRADUATES_PAGE");
    }

    public static Map<String, String> getRedirectPath() {
        return redirectPath;
    }
}
