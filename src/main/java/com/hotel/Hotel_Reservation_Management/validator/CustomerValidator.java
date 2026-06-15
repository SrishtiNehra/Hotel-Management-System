package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    public void validate(CustomerDto dto) {

        if (dto.getPhoneNumber() == null || !dto.getPhoneNumber().matches("[0-9]{10}")) {
            throw new RuntimeException("Phone number must be 10 digits");
        }

        if (dto.getEmail() == null || !dto.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email");
        }

        if (dto.getFullName() == null || dto.getFullName().trim().isEmpty()) {
            throw new RuntimeException("Full name is required");
        }
    }
}