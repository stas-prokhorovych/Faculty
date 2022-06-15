package com.example.model.utils;

import java.util.HashMap;
import java.util.Map;

public class Validator {
    private Validator() {
    }

    public static boolean isLoginValid(final String login) {
        return login != null && login.matches("^[a-z\\d_-]{2,16}$");
    }

    public static boolean isPasswordValid(final String password) {
        return password != null && password.length() >= 2 && password.length() <= 64;
    }

    public static boolean isPasswordCorrect(final String givenPassword, final String realPassword) {
        if (givenPassword == null || realPassword == null)
            return false;
        return givenPassword.equals(realPassword);
    }

    public static boolean isEmailValid(final String email) {
        return email != null && email.matches("^(.+)@(\\S+)$");
    }

    public static Map<String, String> checkLoginForm(String login, String password) {
        boolean errorOccur = false;
        Map<String, String> validInputs = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        if (!isLoginValid(login)) {
            errorOccur = true;
            errors.put("loginError", "Login is not valid");
        } else {
            validInputs.put("validLogin", login);
        }

        if (!isPasswordValid(password)) {
            errorOccur = true;
            errors.put("passwordError", "Password in not valid");
        } else {
            validInputs.put("validPassword", password);
        }

        if(errorOccur) {
            errors.putAll(validInputs);
        }

        return errors;
    }
}
