package com.example.model.utils;

import java.util.HashMap;
import java.util.Map;

public class FormValidator {
    private FormValidator() {
    }

    public static Map<String, String> checkLoginForm(String login, String password) {
        boolean errorOccur = false;
        Map<String, String> validInputs = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        if (login == null || login.equals("")) {
            errorOccur = true;
            errors.put("loginError", "Login is empty, please input your information");
        } else if (!Validator.isLoginValid(login)) {
            errorOccur = true;
            errors.put("loginError", "Login is not valid");
        } else {
            validInputs.put("validLogin", login);
        }

        if (password == null || password.equals("")) {
            errorOccur = true;
            errors.put("passwordError", "Password is empty, please input your information");
        } else if (!Validator.isPasswordValid(password)) {
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
        } else if (!Validator.isLoginValid(login)) {
            errorOccur = true;
            errors.put("loginError", "Login is not valid");
        } else {
            validInputs.put("validLogin", login);
        }

        if (password == null || password.equals("")) {
            errorOccur = true;
            errors.put("passwordError", "Password is empty, please input your information");
        } else if (!Validator.isPasswordValid(password)) {
            errorOccur = true;
            errors.put("passwordError", "Password in not valid");
        } else {
            validInputs.put("validPassword", password);
        }

        if (repeatPassword == null || repeatPassword.equals("")) {
            errorOccur = true;
            errors.put("repeatPasswordError", "Password repeat is empty, please input your information");
        } else if (!Validator.isPasswordsMatch(password, repeatPassword)) {
            errorOccur = true;
            errors.put("repeatPasswordError", "Repeat password didn't match");
        } else {
            validInputs.put("validRepeatPassword", repeatPassword);
        }

        if (email == null || email.equals("")) {
            errorOccur = true;
            errors.put("emailError", "Email is empty, please input your information");
        } else if (!Validator.isEmailValid(email)) {
            errorOccur = true;
            errors.put("emailError", "Email in not valid");
        } else {
            validInputs.put("validEmail", email);
        }

        if (firstName == null || firstName.equals("")) {
            errorOccur = true;
            errors.put("firstNameError", "First Name is empty, please input your information");
        } else if (!Validator.isFirstNameValid(firstName)) {
            errorOccur = true;
            errors.put("firstNameError", "First Name in not valid");
        } else {
            validInputs.put("validFirstName", firstName);
        }

        if (lastName == null || lastName.equals("")) {
            errorOccur = true;
            errors.put("lastNameError", "Last Name is empty, please input your information");
        } else if (!Validator.isLastNameValid(lastName)) {
            errorOccur = true;
            errors.put("lastNameError", "Last Name in not valid");
        } else {
            validInputs.put("validLastName", lastName);
        }

        if (phone != null && !phone.equals("")) {
            if(!Validator.isPhoneValid(phone)) {
                errorOccur = true;
                errors.put("phoneError", "Phone in not valid");
            }
        } else {
            validInputs.put("phoneError", phone);
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
        } else if (!Validator.isCourseNameValid(name)) {
            errorOccur = true;
            errors.put("nameError", "Course name in not valid");
        } else {
            validInputs.put("validName", name);
        }

        if (theme == null || theme.equals("")) {
            errorOccur = true;
            errors.put("themeError", "Course theme is empty, please input your information");
        } else if (!Validator.isCourseThemeValid(theme)) {
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
