package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.BillingDto;
import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;
import com.hotel.Hotel_Reservation_Management.mapper.BillingMapper;
import com.hotel.Hotel_Reservation_Management.repository.BillingRepository;
import com.hotel.Hotel_Reservation_Management.repository.ReservationRepository;
import com.hotel.Hotel_Reservation_Management.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public BillingDto createBill(BillingDto dto) {

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Billing billing = BillingMapper.toEntity(dto, reservation);

        billing.setPaymentDate(LocalDate.now());

        // default status if not provided
        if (billing.getPaymentStatus() == null) {
            billing.setPaymentStatus(PaymentStatus.PENDING);
        }

        return BillingMapper.toDto(billingRepository.save(billing));
    }

    @Override
    public BillingDto getBillById(Long id) {

        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        return BillingMapper.toDto(billing);
    }

    @Override
    public BillingDto getBillByReservation(Long reservationId) {

        Billing billing = billingRepository.findByReservation_ReservationId(reservationId);

        if (billing == null) {
            throw new RuntimeException("Billing not found for reservation");
        }

        return BillingMapper.toDto(billing);
    }

    @Override
    public List<BillingDto> getAllBills() {
        return billingRepository.findAll()
                .stream()
                .map(BillingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillingDto updateBill(Long id, BillingDto dto) {

        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        billing.setAmount(dto.getAmount());
        billing.setPaymentStatus(dto.getPaymentStatus());

        return BillingMapper.toDto(billingRepository.save(billing));
    }
}