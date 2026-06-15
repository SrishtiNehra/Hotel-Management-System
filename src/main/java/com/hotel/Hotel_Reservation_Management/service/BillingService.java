package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.BillingDto;
import java.util.List;

public interface BillingService {

    BillingDto createBill(BillingDto billingDto);

    BillingDto getBillById(Long id);

    BillingDto getBillByReservation(Long reservationId);

    List<BillingDto> getAllBills();

    BillingDto updateBill(Long id, BillingDto billingDto);
}