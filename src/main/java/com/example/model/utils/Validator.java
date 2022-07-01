package com.example.model.utils;

/**
 * Makes validation of all input data.
 */
public class Validator {
    private Validator() {
    }

    /**
     * Checks {@link String} for match regex
     *
     * @param login a {@link String} value
     * @return {@code true} if value is fit regex and {@code false} otherwise
     */
    public static boolean isLoginValid(final String login) {
        return login.matches("^[a-z\\d_-]{4,16}$");
    }

    public static boolean isPasswordValid(final String password) {
        return password.length() > 2 && password.length() <= 64;
    }

    public static boolean isPasswordsMatch(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public static boolean isEmailValid(final String email) {
        return email.matches("^[a-zA-Z\\d_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z\\d.-]+$");
    }

    public static boolean isFirstNameValid(final String firstName) {
        return firstName.matches("[A-Z][a-z]{3,20}");
    }

    public static boolean isLastNameValid(final String lastName) {
        return lastName.matches("[A-Z][a-z]{1,20}");
    }

    public static boolean isPhoneValid(final String phone) {
        return phone.matches("^\\d{10}$");
    }

    public static boolean isCourseNameValid(final String courseName) {
        return courseName.matches("[A-Z]*[a-z\\d\\s]{0,20}");
    }

    public static boolean isCourseThemeValid(final String courseTheme) {
        return courseTheme.matches("[A-Z]*[A-Za-z\\s]{1,20}");
    }
}
