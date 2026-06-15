package com.hotel.Hotel_Reservation_Management.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    // calculate number of days between check-in and check-out
    public static long calculateDays(LocalDate checkIn, LocalDate checkOut) {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    // check if date is in past
    public static boolean isPastDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    // validate date range
    public static boolean isValidRange(LocalDate start, LocalDate end) {
        return start != null && end != null && !start.isAfter(end);
    }
}