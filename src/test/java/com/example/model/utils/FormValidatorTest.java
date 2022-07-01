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



//        Map<String, String> result3 = FormValidator.checkSignupForm("vla", "12");
//        assertTrue(result3.containsKey("loginError"));
//        assertTrue(result3.containsKey("passwordError"));
//        assertTrue(result3.containsValue("Login is not valid"));
//        assertTrue(result3.containsValue("Password in not valid"));
//
//        Map<String, String> result4 = FormValidator.checkSignupForm("vlad44", "12");
//        assertTrue(result4.containsKey("validLogin"));
//        assertTrue(result4.containsKey("passwordError"));
//        assertTrue(result4.containsValue("vlad44"));
//        assertTrue(result4.containsValue("Password in not valid"));


    }
}
