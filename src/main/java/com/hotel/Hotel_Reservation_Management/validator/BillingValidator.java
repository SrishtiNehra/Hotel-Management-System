package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.BillingDTO;
import org.springframework.stereotype.Component;
import com.hotel.Hotel_Reservation_Management.exception.BadRequestException;

import java.math.BigDecimal;

@Component
public class BillingValidator {

    public void validate(BillingDTO dto) {

        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be greater than 0");
        }

        if (dto.getReservationId() == null) {
            throw new BadRequestException("Reservation ID is required");
        }

        if (dto.getPaymentStatus() == null) {
            throw new BadRequestException("Payment status is required");
        }
    }
}