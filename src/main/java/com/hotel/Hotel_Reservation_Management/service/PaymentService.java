package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO pay(Long reservationId);
    PaymentDTO getPaymentByReservation(Long reservationId);
}