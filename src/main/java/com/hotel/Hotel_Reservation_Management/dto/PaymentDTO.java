package com.hotel.Hotel_Reservation_Management.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {

    private Long paymentId;
    private Long reservationId;
    private Double amount;
    private String status;
    private LocalDateTime paymentDate;
}