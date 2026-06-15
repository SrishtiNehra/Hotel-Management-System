package com.hotel.Hotel_Reservation_Management.dto;

import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BillingDto {

    private Long billingId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

    @NotNull
    private LocalDate paymentDate;

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private Long reservationId;
}