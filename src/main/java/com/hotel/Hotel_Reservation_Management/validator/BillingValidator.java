package com.hotel.Hotel_Reservation_Management.validator;

import com.hotel.Hotel_Reservation_Management.dto.BillingDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BillingValidator {

    public void validate(BillingDto dto) {

        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        if (dto.getReservationId() == null) {
            throw new RuntimeException("Reservation ID required");
        }
    }
}