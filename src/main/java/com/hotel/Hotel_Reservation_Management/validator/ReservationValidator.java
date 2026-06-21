package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDTO;
import org.springframework.stereotype.Component;
import com.hotel.Hotel_Reservation_Management.exception.BadRequestException;

import java.time.LocalDate;

@Component
public class ReservationValidator {

    public void validate(ReservationDTO dto) {

        if (dto.getPlannedCheckIn() == null || dto.getPlannedCheckOut() == null) {
            throw new BadRequestException("Check-in and check-out dates are required");
        }

        if (dto.getPlannedCheckIn().isAfter(dto.getPlannedCheckOut())) {
            throw new BadRequestException("Check-in cannot be after check-out");
        }

        if (dto.getPlannedCheckIn().isBefore(LocalDate.now())) {
            throw new BadRequestException("Check-in date cannot be in the past");
        }

        if (dto.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID is required");
        }

        if (dto.getRoomId() == null) {
            throw new IllegalArgumentException("Room ID is required");
        }
    }
}