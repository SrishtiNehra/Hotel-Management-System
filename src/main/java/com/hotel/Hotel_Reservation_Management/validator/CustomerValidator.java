package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    public void validate(CustomerDTO dto) {

        if (dto.getFullName() == null || dto.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required");
        }

        if (dto.getPhoneNumber() == null || !dto.getPhoneNumber().matches("^[0-9]{10}$")) {
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }

        if (dto.getEmail() == null || !dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}