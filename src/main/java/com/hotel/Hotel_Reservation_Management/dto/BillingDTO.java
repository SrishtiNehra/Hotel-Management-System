package com.hotel.Hotel_Reservation_Management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingDTO {

    private Long billingId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Reservation ID is required")
    private Long reservationId;
}