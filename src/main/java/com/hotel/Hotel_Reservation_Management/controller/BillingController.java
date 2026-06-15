package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.BillingDTO;
import com.hotel.Hotel_Reservation_Management.service.BillingService;
import com.hotel.Hotel_Reservation_Management.validator.BillingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billings")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private BillingValidator billingValidator;

    @PostMapping
    public BillingDTO create(@RequestBody BillingDTO dto) {
        billingValidator.validate(dto);
        return billingService.createBill(dto);
    }

    @GetMapping("/{id}")
    public BillingDTO getById(@PathVariable Long id) {
        return billingService.getBillById(id);
    }

    @GetMapping("/reservation/{reservationId}")
    public BillingDTO getByReservation(@PathVariable Long reservationId) {
        return billingService.getBillByReservation(reservationId);
    }

    @GetMapping
    public List<BillingDTO> getAll() {
        return billingService.getAllBills();
    }

    @PutMapping("/{id}")
    public BillingDTO update(@PathVariable Long id, @RequestBody BillingDTO dto) {
        return billingService.updateBill(id, dto);
    }
}