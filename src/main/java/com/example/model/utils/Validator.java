package com.example.model.utils;

import java.util.HashMap;
import java.util.Map;

public class Validator {
    private Validator() {
    }

    public static boolean isLoginValid(final String login) {
        return login.matches("^[a-z\\d_-]{2,16}$");
    }

    public static boolean isPasswordValid(final String password) {
        return password.length() >= 2 && password.length() <= 64;
    }

    public static boolean isPasswordCorrect(final String givenPassword, final String realPassword) {
        return givenPassword.equals(realPassword);
    }

    private static boolean isPasswordsMatch(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public static boolean isEmailValid(final String email) {
        return email.matches("^(.+)@(\\S+)$");
    }

    public static boolean isFirstNameValid(final String firstName) {
        return firstName.matches("[A-Z][a-z]{1,20}");
    }

    public static boolean isLastNameValid(final String lastName) {
        return lastName.matches("[A-Z][a-z]{1,20}");
    }

    public static boolean isPhoneValid(final String phone) {
        return true;
    }

    public static boolean isCourseNameValid(final String courseName) {
        return true;
    }

    public static boolean isCourseThemeValid(final String courseTheme) {
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
            errors.put("repeatPasswordError", "Repeat password didn't match");
        } else {
            validInputs.put("validRepeatPassword", repeatPassword);
        }

        if (email == null || email.equals("")) {
            errorOccur = true;
            errors.put("emailError", "Email is empty, please input your information");
        } else if (!isEmailValid(email)) {
            errorOccur = true;
            errors.put("emailError", "Email in not valid");
        } else {
            validInputs.put("validEmail", email);
        }

        if (firstName == null || firstName.equals("")) {
            errorOccur = true;
            errors.put("firstNameError", "First Name is empty, please input your information");
        } else if (!isFirstNameValid(firstName)) {
            errorOccur = true;
            errors.put("firstNameError", "Email in not valid");
        } else {
            validInputs.put("validFirstName", firstName);
        }

        if (lastName == null || lastName.equals("")) {
            errorOccur = true;
            errors.put("lastNameError", "Last Name is empty, please input your information");
        } else if (!isLastNameValid(lastName)) {
            errorOccur = true;
            errors.put("lastNameError", "Last Name in not valid");
        } else {
            validInputs.put("validLastName", firstName);
        }

        if (phone == null || phone.equals("")) {
            if(!isPhoneValid(phone)) {
                errorOccur = true;
                errors.put("phoneError", "Last Name in not valid");
            }
        } else {
            validInputs.put("validPhone", firstName);
        }

        if (errorOccur) {
            errors.putAll(validInputs);
        }

        return errors;
    }

    public static Map<String, String> checkAddCourseForm(String name, String theme, String startDate, String endDate) {
        boolean errorOccur = false;
        Map<String, String> validInputs = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        if (name == null || name.equals("")) {
            errorOccur = true;
            errors.put("nameError", "Name is empty, please input your information");
        } else if (!isCourseNameValid(name)) {
            errorOccur = true;
            errors.put("nameError", "Course name in not valid");
        } else {
            validInputs.put("validName", name);
        }

        if (theme == null || theme.equals("")) {
            errorOccur = true;
            errors.put("themeError", "Course theme is empty, please input your information");
        } else if (!isCourseThemeValid(theme)) {
            errorOccur = true;
            errors.put("themeError", "Course theme in not valid");
        } else {
            validInputs.put("validTheme", theme);
        }

        if (startDate == null || startDate.equals("")) {
            errorOccur = true;
            errors.put("startDateError", "Course start date is empty, please input your information");
        } else {
            validInputs.put("validStartDate", startDate);
        }

        if (endDate == null || endDate.equals("")) {
            errorOccur = true;
            errors.put("endDateError", "Course end date is empty, please input your information");
        } else {
            validInputs.put("validEndDate", endDate);
        }

        if (errorOccur) {
            errors.putAll(validInputs);
        }
        return errors;
    }
}
