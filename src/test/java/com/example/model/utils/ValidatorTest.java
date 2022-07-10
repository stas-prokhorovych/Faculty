package com.example.model.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {
    @Test
    void testIsLoginValid() {
        assertTrue(Validator.isLoginValid("hello33"));
        assertTrue(Validator.isLoginValid("oleg22s"));
        assertTrue(Validator.isLoginValid("ole_g22s"));
        assertTrue(Validator.isLoginValid("ole-g22s"));
        assertFalse(Validator.isLoginValid("hello&x"));
        assertFalse(Validator.isLoginValid(")fffff"));
        assertFalse(Validator.isLoginValid("zzs****ss"));
    }

    @Test
    void testIsPasswordValid() {
        assertFalse(Validator.isPasswordValid("12"));
        assertTrue(Validator.isPasswordValid("3425fggfd"));
        assertTrue(Validator.isPasswordValid("2340^fsfd"));
        assertTrue(Validator.isPasswordValid("asdfasdf03)3"));
        assertFalse(Validator.isPasswordValid("z"));
        assertTrue(Validator.isPasswordValid("1234567890123456789012345678901234567890123456789012345678901234"));
    }

    @Test
    void testisPasswordsMatch() {
        assertFalse(Validator.isPasswordsMatch("1234", "12345"));
        assertTrue(Validator.isPasswordsMatch("s672", "s672"));
    }

    @Test
    void testIsEmailValid() {
        assertTrue(Validator.isEmailValid("stas.prokhorovych@gmail.com"));
        assertFalse(Validator.isEmailValid("stas.prokhorovychgmail.com"));
        assertFalse(Validator.isEmailValid("stas.prokhoro()vych@gmailcom"));
    }

    @Test
    void testIsFirstNameValid() {
        assertTrue(Validator.isLastNameValid("Stas"));
        assertTrue(Validator.isLastNameValid("Stanislav"));
        assertFalse(Validator.isFirstNameValid("sie"));
        assertFalse(Validator.isFirstNameValid("stas"));
    }

    @Test
    void testIsLastNameValid() {
        assertTrue(Validator.isLastNameValid("Olegovich"));
        assertFalse(Validator.isFirstNameValid("olegovich"));
        assertFalse(Validator.isFirstNameValid("olg"));
    }

    @Test
    void testIsPhoneValid() {
        assertTrue(Validator.isPhoneValid("1234567890"));
        assertFalse(Validator.isPhoneValid("324"));
        assertFalse(Validator.isPhoneValid("++3402324"));
    }

    @Test
    void testIsCourseThemeValid() {
        assertTrue(Validator.isCourseThemeValid("Computer Science"));
        assertTrue(Validator.isCourseThemeValid("Business"));
    }
}
