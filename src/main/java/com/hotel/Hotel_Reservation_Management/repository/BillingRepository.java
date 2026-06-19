package com.hotel.Hotel_Reservation_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing, Long> {

    List<Billing> findByPaymentStatus(PaymentStatus status);

    Optional<Billing> findByReservation_ReservationId(Long reservationId);
    
    @Query("SELECT COALESCE(SUM(b.amount), 0) FROM Billing b WHERE b.paymentStatus = 'PAID'")
    BigDecimal sumAllRevenue();

    @Query("SELECT b FROM Billing b WHERE b.reservation.customer.customerId = :customerId")
    List<Billing> findByCustomerId(Long customerId);
}