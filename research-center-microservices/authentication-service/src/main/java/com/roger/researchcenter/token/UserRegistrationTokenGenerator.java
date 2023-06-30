package com.roger.researchcenter.token;

import java.util.Random;

public class UserRegistrationTokenGenerator {
    private static final String LETTERS_AND_DIGITS = "ABCDEFGHIJKLMOPQRSUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int TOKEN_LENGTH = 32;
    private static final Random random = new Random();
    private UserRegistrationTokenGenerator() {
    }

    public static String generateToken() {

        StringBuilder sb = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++)
            sb.append(LETTERS_AND_DIGITS.charAt(random.nextInt(LETTERS_AND_DIGITS.length())));
        return sb.toString();
    }
}
