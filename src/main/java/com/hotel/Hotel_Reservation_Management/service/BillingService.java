package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.BillingDTO;
import java.util.List;

public interface BillingService {

    BillingDTO createBill(BillingDTO billingDTO);

    BillingDTO getBillById(Long id);

    BillingDTO getBillByReservation(Long reservationId);

    List<BillingDTO> getAllBills();

    BillingDTO updateBill(Long id, BillingDTO billingDTO);
}