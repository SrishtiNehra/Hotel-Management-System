package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.RoomDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RoomValidator {

    public void validate(RoomDTO dto) {

        if (dto.getRoomNumber() == null || dto.getRoomNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Room number is required");
        }

        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (dto.getHotelId() == null) {
            throw new IllegalArgumentException("Hotel ID is required");
        }

        if (dto.getRoomType() == null) {
            throw new IllegalArgumentException("Room type is required");
        }

        if (dto.getStatus() == null) {
            throw new IllegalArgumentException("Room status is required");
        }
    }
}