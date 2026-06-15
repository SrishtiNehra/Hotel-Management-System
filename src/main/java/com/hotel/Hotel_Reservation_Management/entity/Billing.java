package com.hotel.Hotel_Reservation_Management.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "billings")
@Getter
@Setter
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingId;

    private BigDecimal amount;

    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}