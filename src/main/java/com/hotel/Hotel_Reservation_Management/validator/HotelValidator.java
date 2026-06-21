package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.HotelDTO;
import org.springframework.stereotype.Component;
import com.hotel.Hotel_Reservation_Management.exception.BadRequestException;

@Component
public class HotelValidator {

    public void validate(HotelDTO dto) {

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new BadRequestException("Hotel name is required");
        }

        if (dto.getCity() == null || dto.getCity().trim().isEmpty()) {
            throw new BadRequestException("City is required");
        }

        if (dto.getContactNumber() == null || !dto.getContactNumber().matches("^[0-9]{10}$")) {
            throw new BadRequestException("Contact number must be 10 digits");
        }

        if (dto.getEmail() == null || !dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new BadRequestException("Invalid email format");
        }
    }
}