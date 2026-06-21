package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.PaymentDTO;
import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.entity.Payment;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;
import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import com.hotel.Hotel_Reservation_Management.exception.ResourceNotFoundException;
import com.hotel.Hotel_Reservation_Management.repository.BillingRepository;
import com.hotel.Hotel_Reservation_Management.repository.PaymentRepository;
import com.hotel.Hotel_Reservation_Management.repository.ReservationRepository;
import com.hotel.Hotel_Reservation_Management.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private final PaymentRepository paymentRepository;

    @Autowired
    private final ReservationRepository reservationRepository;

    @Autowired
    private final BillingRepository billingRepository;

    @Override
    public PaymentDTO pay(Long reservationId, double amountFromFrontend) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation not found with id: " + reservationId
                ));

        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setAmount(amountFromFrontend);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PAID);

        paymentRepository.save(payment);

        reservation.setStatus(ReservationStatus.CHECKED_OUT);
        reservationRepository.save(reservation);

        Billing billing = new Billing();
        billing.setAmount(BigDecimal.valueOf(amountFromFrontend));
        billing.setPaymentDate(java.time.LocalDate.now());
        billing.setPaymentStatus(PaymentStatus.PAID);
        billing.setReservation(reservation);

        billingRepository.save(billing);

        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setReservationId(reservationId);
        dto.setAmount(amountFromFrontend);
        dto.setStatus(payment.getStatus().name());
        dto.setPaymentDate(payment.getPaymentDate());

        return dto;
    }

    @Override
    public PaymentDTO getPaymentByReservation(Long reservationId) {
        return null;
    }

    @Override
    public PaymentDTO pay(Long reservationId) {
        return null;
    }
}