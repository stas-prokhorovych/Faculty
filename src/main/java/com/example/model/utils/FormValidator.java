package com.example.model.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Validation of input forms
 */
public class FormValidator {
    private FormValidator() {
    }

    /**
     * @param login    login of the user
     * @param password password of the user
     * @return nothing if input valid error array(with valid inputs) otherwise
     */
    public static Map<String, String> checkLoginForm(String login, String password) {
        boolean errorOccur = false;
        Map<String, String> validInputs = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

//        ResourceBundle rb = getBundleInfo(request);

        if (login == null || login.equals("")) {
            errorOccur = true;
            errors.put("loginError", "Login is empty, please input your information");
        } else if (!Validator.isLoginValid(login)) {
            errorOccur = true;
            errors.put("loginError", "Login is not valid(4-16 symbols a-z and - _)");
        } else {
            validInputs.put("validLogin", login);
        }
        if (password == null || password.equals("")) {
            errorOccur = true;
            errors.put("passwordError", "Password is empty, please input your information");
        } else if (!Validator.isPasswordValid(password)) {
            errorOccur = true;
            errors.put("passwordError", "Password is not valid(more than 2 characters)");
        } else {
            validInputs.put("validPassword", password);
        }

        if (errorOccur) {
            errors.putAll(validInputs);
        }

        return errors;
    }

    /**
     * @param login          login of the user
     * @param password       password of the user
     * @param repeatPassword repeat password of the user
     * @param email          email of the user
     * @param firstName      first name of the user
     * @param lastName       last name of the user
     * @param phone          phone of the user
     * @return nothing if input valid error array(with valid inputs) otherwise
     */
    public static Map<String, String> checkSignupForm(String login, String password, String repeatPassword,
                                                      String email, String firstName, String lastName, String phone) {
        boolean errorOccur = false;
        Map<String, String> validInputs = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

//        ResourceBundle rb = getBundleInfo(request);

        if (login == null || login.equals("")) {
            errorOccur = true;
            errors.put("loginError", "Login is empty, please input your information");
        } else if (!Validator.isLoginValid(login)) {
            errorOccur = true;
            errors.put("loginError", "Login is not valid(4-16 symbols a-z and - _)");
        } else {
            validInputs.put("validLogin", login);
        }

        if (password == null || password.equals("")) {
            errorOccur = true;
            errors.put("passwordError", "Password is empty, please input your information");
        } else if (!Validator.isPasswordValid(password)) {
            errorOccur = true;
            errors.put("passwordError", "Password is not valid(more than 2 characters)");
        } else {
            validInputs.put("validPassword", password);
        }

        if (password == null || repeatPassword == null || repeatPassword.equals("")) {
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
            errors.put("emailError", "Email is not valid(example: oleg@gmail.com)");
        } else {
            validInputs.put("validEmail", email);
        }

        if (firstName == null || firstName.equals("")) {
            errorOccur = true;
            errors.put("firstNameError", "First Name is empty, please input your information");
        } else if (!Validator.isFirstNameValid(firstName)) {
            errorOccur = true;
            errors.put("firstNameError", "First Name is not valid(example: Victor)");
        } else {
            validInputs.put("validFirstName", firstName);
        }

        if (lastName == null || lastName.equals("")) {
            errorOccur = true;
            errors.put("lastNameError", "Last Name is empty, please input your information");
        } else if (!Validator.isLastNameValid(lastName)) {
            errorOccur = true;
            errors.put("lastNameError", "Last Name is not valid(example: Pelosi)");
        } else {
            validInputs.put("validLastName", lastName);
        }

        if (phone != null && !phone.equals("")) {
            if (!Validator.isPhoneValid(phone)) {
                errorOccur = true;
                errors.put("phoneError", "Phone is not valid(example: +380976504426)");
            } else {
                validInputs.put("validPhone", phone);
            }
        }

        if (errorOccur) {
            errors.putAll(validInputs);
        }

        return errors;
    }

    /**
     * @param name      name of the course
     * @param theme     theme of the course
     * @param startDate start date of the course
     * @param endDate   end date of the course
     * @return nothing if input valid error array(with valid inputs) otherwise
     */
    public static Map<String, String> checkAddCourseForm(String name, String theme, String startDate, String endDate) {
        boolean errorOccur = false;
        Map<String, String> validInputs = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

//        ResourceBundle rb = getBundleInfo(request);

        if (name == null || name.equals("")) {
            errorOccur = true;
            errors.put("nameError", "Name is empty, please input your information");
        } else {
            validInputs.put("validName", name);
        }

        if (theme == null || theme.equals("")) {
            errorOccur = true;
            errors.put("themeError", "Course theme is empty, please input your information");
        } else if (!Validator.isCourseThemeValid(theme)) {
            errorOccur = true;
            errors.put("themeError", "Course theme is not valid");
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

//    private static ResourceBundle getBundleInfo(HttpServletRequest request) {
//        String attr = (String) request.getSession().getAttribute("lang");
//        if(attr == null || attr.equals("en")) {
//            return ResourceBundle.getBundle("/resources", new Locale("en", "EN"));
//        }
//        return ResourceBundle.getBundle("/resources", new Locale("uk", "UK"));
//    }
}
