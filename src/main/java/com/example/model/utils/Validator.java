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

    /**
     * Checks {@link String} for match regex
     *
     * @param password a {@link String} value
     * @return {@code true} if value is fit regex and {@code false} otherwise
     */
    public static boolean isPasswordValid(final String password) {
        return password.length() > 2 && password.length() <= 64;
    }

    /**
     * Checks that passwords the same
     *
     * @param password a {@link String} value
     * @param repeatPassword a {@link String} value
     * @return {@code true} if value is fit regex and {@code false} otherwise
     */
    public static boolean isPasswordsMatch(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    /**
     * Checks {@link String} for match regex
     *
     * @param email a {@link String} value
     * @return {@code true} if value is fit regex and {@code false} otherwise
     */
    public static boolean isEmailValid(final String email) {
        return email.matches("^[a-zA-Z\\d_.]+@[a-zA-Z\\d.-]+$");
    }

    /**
     * Checks {@link String} for match regex
     *
     * @param firstName a {@link String} value
     * @return {@code true} if value is fit regex and {@code false} otherwise
     */
    public static boolean isFirstNameValid(final String firstName) {
        return firstName.matches("[A-Z][a-z]{3,20}");
    }

    /**
     * Checks {@link String} for match regex
     *
     * @param lastName a {@link String} value
     * @return {@code true} if value is fit regex and {@code false} otherwise
     */
    public static boolean isLastNameValid(final String lastName) {
        return lastName.matches("[A-Z][a-z]{3,20}");
    }

    /**
     * Checks {@link String} for match regex
     *
     * @param phone a {@link String} value
     * @return {@code true} if value is fit regex and {@code false} otherwise
     */
    public static boolean isPhoneValid(final String phone) {
        return phone.matches("^\\+\\d{12}$");
    }

    /**
     * Checks {@link String} for match regex
     *
     * @param courseTheme a {@link String} value
     * @return {@code true} if value is fit regex and {@code false} otherwise
     */
    public static boolean isCourseThemeValid(final String courseTheme) {
        return courseTheme.matches("[A-Z]*[A-Za-z\\s]{3,20}");
    }
}
