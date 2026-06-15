package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReservationValidator {

    public void validate(ReservationDto dto) {

        if (dto.getPlannedCheckIn() == null || dto.getPlannedCheckOut() == null) {
            throw new RuntimeException("Check-in and check-out required");
        }

        if (dto.getPlannedCheckIn().isAfter(dto.getPlannedCheckOut())) {
            throw new RuntimeException("Check-in cannot be after check-out");
        }

        if (dto.getPlannedCheckIn().isBefore(LocalDate.now())) {
            throw new RuntimeException("Check-in date cannot be in past");
        }

        if (dto.getCustomerId() == null) {
            throw new RuntimeException("Customer ID required");
        }

        if (dto.getRoomId() == null) {
            throw new RuntimeException("Room ID required");
        }
    }
}