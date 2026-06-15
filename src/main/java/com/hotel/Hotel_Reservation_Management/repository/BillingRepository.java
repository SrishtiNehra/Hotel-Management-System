package com.hotel.Hotel_Reservation_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;

import java.util.List;
import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing, Long> {

    List<Billing> findByPaymentStatus(PaymentStatus status);

    Optional<Billing> findByReservation_ReservationId(Long reservationId);
}