package com.example.model.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FormValidatorTest {
    @Test
    void testCheckLoginForm() {
        Map<String, String> result = FormValidator.checkLoginForm("", "");
        assertTrue(result.containsKey("loginError"));
        assertTrue(result.containsKey("passwordError"));
        assertTrue(result.containsValue("Login is empty, please input your information"));
        assertTrue(result.containsValue("Password is empty, please input your information"));

        Map<String, String> result2 = FormValidator.checkLoginForm("vlad33sm", "12355");
        assertTrue(result2.isEmpty());

        Map<String, String> result3 = FormValidator.checkLoginForm("vla", "12");
        assertTrue(result3.containsKey("loginError"));
        assertTrue(result3.containsKey("passwordError"));
        assertTrue(result3.containsValue("Login is not valid"));
        assertTrue(result3.containsValue("Password in not valid"));

        Map<String, String> result4 = FormValidator.checkLoginForm("vlad44", "12");
        assertTrue(result4.containsKey("validLogin"));
        assertTrue(result4.containsKey("passwordError"));
        assertTrue(result4.containsValue("vlad44"));
        assertTrue(result4.containsValue("Password in not valid"));
    }

    @Test
    void testCheckSignupForm() {
        Map<String, String> result = FormValidator.checkSignupForm("", "",
                "", "", "", "", "");
        assertTrue(result.containsKey("loginError"));
        assertTrue(result.containsKey("passwordError"));
        assertTrue(result.containsValue("Login is empty, please input your information"));
        assertTrue(result.containsValue("Password is empty, please input your information"));

        Map<String, String> result2 = FormValidator.checkSignupForm("vlad33sm", "12345",
                "12345", "prokhorovych@gmail.com", "Stan", "Prokhorovych", "");
        assertTrue(result2.isEmpty());

        Map<String, String> result3 = FormValidator.checkSignupForm("()****/", "1",
                "12345", "prokhorovychgmail.com", "stan", "prokhorovych", "343");
        assertTrue(result3.containsValue("Login is not valid"));
        assertTrue(result3.containsValue("Password is not valid"));
        assertTrue(result3.containsValue("Repeat password didn't match"));
        assertTrue(result3.containsValue("Email is not valid"));
        assertTrue(result3.containsValue("First Name is not valid"));
        assertTrue(result3.containsValue("Last Name is not valid"));
        assertTrue(result3.containsValue("Phone is not valid"));
    }

    @Test
    void checkAddCourseForm() {
        Map<String, String> result = FormValidator.checkAddCourseForm("SQL", "Computer Science",
                "2022-07-04T17:38", "2022-07-04T17:38");
        assertTrue(result.isEmpty());

        Map<String, String> result2 = FormValidator.checkAddCourseForm("", "",
                "", "");
        assertTrue(result2.containsKey("nameError"));
        assertTrue(result2.containsKey("themeError"));
        assertTrue(result2.containsKey("startDateError"));
        assertTrue(result2.containsKey("endDateError"));

        Map<String, String> result3 = FormValidator.checkAddCourseForm("sd", "cv",
                "", "");
        assertTrue(result3.containsValue("Course name is not valid"));
        assertTrue(result3.containsValue("Course theme is not valid"));
    }
}
