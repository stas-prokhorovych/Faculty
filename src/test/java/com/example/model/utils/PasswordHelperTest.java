package com.example.model.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHelperTest {

    @Test
    void testEncrypt() {
        assertEquals("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3", PasswordHelper.encrypt("123"));
        assertEquals("3bcf66ad6504fdeacc684bdffe1132595d0ee86e20ad3c51a4842f5e0df8f20f", PasswordHelper.encrypt("5674s"));
    }

    @Test
    void testMatch() {
        assertTrue(PasswordHelper.match("123", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3"));
        assertTrue(PasswordHelper.match("5674s", "3bcf66ad6504fdeacc684bdffe1132595d0ee86e20ad3c51a4842f5e0df8f20f"));
        assertFalse(PasswordHelper.match("123", "3bcf66ad6504fdeacc684bdffe1132595d0ee86e20ad3c51a4842f5e0df8f20f"));
    }
}
