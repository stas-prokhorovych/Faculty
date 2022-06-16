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

    private static boolean isPasswordsMatch(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public static boolean isEmailValid(final String email) {
        return email != null && email.matches("^(.+)@(\\S+)$");
    }

    public static boolean isFirstNameValid(final String firstName) {
        return true;
    }

    public static boolean isLastNameValid(final String lastName) {
        return true;
    }

    public static boolean isPhoneValid(final String phone) {
        return true;
    }

    public static Map<String, String> checkLoginForm(String login, String password) {
        boolean errorOccur = false;
        Map<String, String> validInputs = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        if (login == null || login.equals("")) {
            errorOccur = true;
            errors.put("loginError", "Login is empty, please input your information");
        } else if (!isLoginValid(login)) {
            errorOccur = true;
            errors.put("loginError", "Login is not valid");
        } else {
            validInputs.put("validLogin", login);
        }

        if (password == null || password.equals("")) {
            errorOccur = true;
            errors.put("passwordError", "Password is empty, please input your information");
        } else if (!isPasswordValid(password)) {
            errorOccur = true;
            errors.put("passwordError", "Password in not valid");
        } else {
            validInputs.put("validPassword", password);
        }

        if (errorOccur) {
            errors.putAll(validInputs);
        }
        return errors;
    }



    public static Map<String, String> checkSignupForm(String login, String password, String repeatPassword,
                                                      String email, String firstName, String lastName, String phone) {
        boolean errorOccur = false;
        Map<String, String> validInputs = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        if (login == null || login.equals("")) {
            errorOccur = true;
            errors.put("loginError", "Login is empty, please input your information");
        } else if (!isLoginValid(login)) {
            errorOccur = true;
            errors.put("loginError", "Login is not valid");
        } else {
            validInputs.put("validLogin", login);
        }

        if (password == null || password.equals("")) {
            errorOccur = true;
            errors.put("passwordError", "Password is empty, please input your information");
        } else if (!isPasswordValid(password)) {
            errorOccur = true;
            errors.put("passwordError", "Password in not valid");
        } else {
            validInputs.put("validPassword", password);
        }

        if (repeatPassword == null || repeatPassword.equals("")) {
            errorOccur = true;
            errors.put("repeatPasswordError", "Password repeat is empty, please input your information");
        } else if (!isPasswordsMatch(password, repeatPassword)) {
            errorOccur = true;
            errors.put("repeatPasswordError", "Password in not valid");
        } else {
            validInputs.put("validRepeatPassword", password);
        }

        if (email == null || email.equals("")) {
            errorOccur = true;
            errors.put("emailError", "Email is empty, please input your information");
        } else if (!isEmailValid(email)) {
            errorOccur = true;
            errors.put("emailError", "Email in not valid");
        } else {
            validInputs.put("validEmail", password);
        }



        if (errorOccur) {
            errors.putAll(validInputs);
        }

        return errors;
    }
}
