package com.hotel.Hotel_Reservation_Management.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // encrypt password
    public static String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    // match password
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}