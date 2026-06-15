package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.HotelDto;
import org.springframework.stereotype.Component;

@Component
public class HotelValidator {

    public void validate(HotelDto dto) {

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new RuntimeException("Hotel name required");
        }

        if (dto.getCity() == null || dto.getCity().trim().isEmpty()) {
            throw new RuntimeException("City required");
        }

        if (dto.getContactNumber() == null || !dto.getContactNumber().matches("[0-9]{10}")) {
            throw new RuntimeException("Invalid contact number");
        }
    }
}