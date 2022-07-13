package com.example.model.utils;

import com.example.model.constants.UserAccess;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAccessTest {
    @Test
    public void testUserAccess() {
        List<String> guestUrl = UserAccess.getGuestUrls();
        assertTrue(guestUrl.contains("/css/styling.css"));
    }
}

