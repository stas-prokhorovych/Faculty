package com.example.model.security;

public class Security {
    private Security() {
    }

    public static boolean isPasswordCorrect(final String givenPassword, final String realPassword) {
        if (givenPassword == null || realPassword == null)
            return false;
        return givenPassword.equals(realPassword);
    }


    public static boolean isLoginValid(final String login) {
        return login != null && login.matches("^[a-z\\d_-]{2,16}$");
    }

    public static boolean isEmailValid(final String email)  {
        return email != null && email.matches("^(.+)@(\\S+)$");
    }

    public static boolean isPasswordValid(final String password)  {
        return /*password != null && password.length() >= 8 && */password.length() <= 64;
    }
}
