package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.CustomerDTO;
import org.springframework.stereotype.Component;
import com.hotel.Hotel_Reservation_Management.exception.BadRequestException;

@Component
public class CustomerValidator {

    public void validate(CustomerDTO dto) {

        if (dto.getFullName() == null || dto.getFullName().trim().isEmpty()) {
            throw new BadRequestException("Full name is required");
        }

        if (dto.getPhoneNumber() == null || !dto.getPhoneNumber().matches("^[0-9]{10}$")) {
            throw new BadRequestException("Phone number must be 10 digits");
        }

        if (dto.getEmail() == null || !dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new BadRequestException("Invalid email format");
        }
    }
}