package com.hotel.Hotel_Reservation_Management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double amount;

    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    private Reservation reservation;
}