package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.RoomDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RoomValidator {

    public void validate(RoomDto dto) {

        if (dto.getRoomNumber() == null || dto.getRoomNumber().trim().isEmpty()) {
            throw new RuntimeException("Room number required");
        }

        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price must be greater than 0");
        }

        if (dto.getHotelId() == null) {
            throw new RuntimeException("Hotel ID required");
        }
    }
}