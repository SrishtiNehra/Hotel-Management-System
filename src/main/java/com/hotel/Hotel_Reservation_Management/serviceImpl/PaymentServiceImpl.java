package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.PaymentDTO;
import com.hotel.Hotel_Reservation_Management.entity.Payment;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;
import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import com.hotel.Hotel_Reservation_Management.repository.PaymentRepository;
import com.hotel.Hotel_Reservation_Management.repository.ReservationRepository;
import com.hotel.Hotel_Reservation_Management.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public PaymentDTO pay(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setAmount(2000.0); // you can calculate dynamically
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PAID);

        reservation.setStatus(ReservationStatus.CHECKED_OUT);

        paymentRepository.save(payment);
        reservationRepository.save(reservation);

        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setReservationId(reservationId);
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus().name());
        dto.setPaymentDate(payment.getPaymentDate());

        return dto;
    }

    @Override
    public PaymentDTO getPaymentByReservation(Long reservationId) {

        Payment payment = paymentRepository
                .findByReservation_ReservationId(reservationId);

        if (payment == null) return null;

        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setReservationId(reservationId);
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus().name());
        dto.setPaymentDate(payment.getPaymentDate());

        return dto;
    }
}