package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.AdminDTO;
import org.springframework.stereotype.Component;
import com.hotel.Hotel_Reservation_Management.exception.BadRequestException;

@Component
public class AdminValidator {

    public void validate(AdminDTO dto) {

        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            throw new BadRequestException("Username cannot be empty");
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new BadRequestException("Password must be at least 6 characters");
        }

        if (dto.getEmail() == null || !dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new BadRequestException("Invalid email format");
        }
    }
}