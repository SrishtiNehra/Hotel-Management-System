package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.BillingDTO;
import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;
import com.hotel.Hotel_Reservation_Management.mapper.BillingMapper;
import com.hotel.Hotel_Reservation_Management.repository.BillingRepository;
import com.hotel.Hotel_Reservation_Management.repository.ReservationRepository;
import com.hotel.Hotel_Reservation_Management.service.BillingService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

	@Autowired
    private final BillingRepository billingRepository;
	
	@Autowired
    private final ReservationRepository reservationRepository;

    @Override
    public BillingDTO createBill(BillingDTO dto) {

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Billing billing = BillingMapper.toEntity(dto, reservation);

        billing.setPaymentDate(LocalDate.now());

        if (billing.getPaymentStatus() == null) {
            billing.setPaymentStatus(PaymentStatus.PENDING);
        }

        return BillingMapper.toDTO(billingRepository.save(billing));
    }

    @Override
    public BillingDTO getBillById(Long id) {
        return BillingMapper.toDTO(
                billingRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Billing not found"))
        );
    }

    @Override
    public BillingDTO getBillByReservation(Long reservationId) {

        Billing billing = billingRepository.findByReservation_ReservationId(reservationId)
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        return BillingMapper.toDTO(billing);
    }

    @Override
    public List<BillingDTO> getAllBills() {
        return billingRepository.findAll()
                .stream()
                .map(BillingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BillingDTO updateBill(Long id, BillingDTO dto) {

        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        billing.setAmount(dto.getAmount());
        billing.setPaymentStatus(dto.getPaymentStatus());

        return BillingMapper.toDTO(billingRepository.save(billing));
    }
}