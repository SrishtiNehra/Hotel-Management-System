package com.hotel.Hotel_Reservation_Management.util;

import java.time.LocalDate;

public class ReservationUtil {

    // check valid booking window
    public static void validateBookingDates(LocalDate checkIn, LocalDate checkOut) {

        if (checkIn == null || checkOut == null) {
            throw new RuntimeException("Dates cannot be null");
        }

        if (checkIn.isAfter(checkOut)) {
            throw new RuntimeException("Check-in cannot be after check-out");
        }

        if (checkIn.isBefore(LocalDate.now())) {
            throw new RuntimeException("Check-in cannot be in past");
        }
    }
}