package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.BillingDto;
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
    public BillingDto create(@RequestBody BillingDto dto) {
        billingValidator.validate(dto);
        return billingService.createBill(dto);
    }

    @GetMapping("/{id}")
    public BillingDto getById(@PathVariable Long id) {
        return billingService.getBillById(id);
    }

    @GetMapping("/reservation/{reservationId}")
    public BillingDto getByReservation(@PathVariable Long reservationId) {
        return billingService.getBillByReservation(reservationId);
    }

    @GetMapping
    public List<BillingDto> getAll() {
        return billingService.getAllBills();
    }

    @PutMapping("/{id}")
    public BillingDto update(@PathVariable Long id, @RequestBody BillingDto dto) {
        return billingService.updateBill(id, dto);
    }
}