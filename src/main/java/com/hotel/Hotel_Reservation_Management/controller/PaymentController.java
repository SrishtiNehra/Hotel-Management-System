package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.PaymentDTO;
import com.hotel.Hotel_Reservation_Management.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // PAY
    @PutMapping("/pay/{reservationId}")
    public PaymentDTO pay(@PathVariable Long reservationId) {
        return paymentService.pay(reservationId);
    }

    // GET PAYMENT
    @GetMapping("/{reservationId}")
    public PaymentDTO get(@PathVariable Long reservationId) {
        return paymentService.getPaymentByReservation(reservationId);
    }
}