package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.BillingDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BillingValidator {

    public void validate(BillingDTO dto) {

        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (dto.getReservationId() == null) {
            throw new IllegalArgumentException("Reservation ID is required");
        }

        if (dto.getPaymentStatus() == null) {
            throw new IllegalArgumentException("Payment status is required");
        }
    }
}