package com.example.model.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHelper {
    public static String encrypt(String passwordToEncrypt) {
        return DigestUtils.sha256Hex(passwordToEncrypt);
    }

    public static boolean match(String givenPassword, String realPassword) {
        String hashGivenPassword = DigestUtils.sha256Hex(givenPassword);
        return hashGivenPassword.equals(realPassword);
    }
}
