package com.example.model.security;

public class Security {
    private Security() {
    }

    public static boolean isEmailValid(final String email)  {
        return email != null && email.matches("^(.+)@(\\S+)$");
    }

    public static boolean isPasswordValid(final String password)  {
        return password != null && password.length() >= 8 && password.length() <= 64;
    }
}
