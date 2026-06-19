package com.hotel.Hotel_Reservation_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.Hotel_Reservation_Management.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByReservation_ReservationId(Long reservationId);
}