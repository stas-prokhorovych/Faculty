package com.example.model.constants;

import java.util.HashMap;
import java.util.Map;

import static com.example.model.constants.Pages.*;

public class Prg {
    private Prg() {
    }

    private static Map<String, String> redirectPath = new HashMap<>();

    static {
        redirectPath.put("redirect:" + PROFILE_PAGE, "controller?command=GOTO_PROFILE_PAGE");
        redirectPath.put("redirect:" + PROFILE_PAGE, "controller?command=GOTO_PROFILE_PAGE");
        redirectPath.put("redirect:" + COURSE_CATALOGUE_PAGE, "controller?command=COURSE_CATALOGUE");
        redirectPath.put("redirect:" + SHOW_JOURNAL_PAGE, "controller?command=SHOW_JOURNAL");
//        redirectPath.put("redirect:" + GRADUATES_PAGE, "controller?command=SHOW_JOURNAL");

    }

    public static Map<String, String> getRedirectPath() {
        return redirectPath;
    }
}
