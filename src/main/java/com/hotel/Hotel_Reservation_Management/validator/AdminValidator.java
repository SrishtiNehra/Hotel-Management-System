package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.AdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminValidator {

    public void validate(AdminDto dto) {

        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters");
        }

        if (dto.getEmail() == null || !dto.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email format");
        }
    }
}