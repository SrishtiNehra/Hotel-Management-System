package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.RoomDTO;
import org.springframework.stereotype.Component;
import com.hotel.Hotel_Reservation_Management.exception.BadRequestException;

import java.math.BigDecimal;

@Component
public class RoomValidator {

    public void validate(RoomDTO dto) {

        if (dto.getRoomNumber() == null || dto.getRoomNumber().trim().isEmpty()) {
            throw new BadRequestException("Room number is required");
        }

        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Price must be greater than 0");
        }

        if (dto.getHotelId() == null) {
            throw new BadRequestException("Hotel ID is required");
        }

        if (dto.getRoomType() == null) {
            throw new BadRequestException("Room type is required");
        }

        if (dto.getStatus() == null) {
            throw new BadRequestException("Room status is required");
        }
    }
}