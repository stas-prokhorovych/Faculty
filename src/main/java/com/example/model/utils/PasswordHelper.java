package com.example.model.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Used to create hash of password
 * Used algorithm sha256
 */
public class PasswordHelper {
    /**
     * @param passwordToEncrypt password to which generate hash
     * @return hash
     */
    public static String encrypt(String passwordToEncrypt) {
        return DigestUtils.sha256Hex(passwordToEncrypt);
    }

    /**
     * Check if password the same
     *
     * @param givenPassword password to hash
     * @param realPassword hash
     * @return if two passwords the same
     */
    public static boolean match(String givenPassword, String realPassword) {
        String hashGivenPassword = DigestUtils.sha256Hex(givenPassword);
        return hashGivenPassword.equals(realPassword);
    }
}
