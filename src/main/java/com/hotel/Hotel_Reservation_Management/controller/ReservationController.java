package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDto;
import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import com.hotel.Hotel_Reservation_Management.service.ReservationService;
import com.hotel.Hotel_Reservation_Management.validator.ReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationValidator reservationValidator;

    @PostMapping
    public ReservationDto create(@RequestBody ReservationDto dto) {
        reservationValidator.validate(dto);
        return reservationService.createReservation(dto);
    }

    @GetMapping("/{id}")
    public ReservationDto getById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping
    public List<ReservationDto> getAll() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/customer/{customerId}")
    public List<ReservationDto> getByCustomer(@PathVariable Long customerId) {
        return reservationService.getReservationsByCustomer(customerId);
    }

    @PutMapping("/{id}")
    public ReservationDto update(@PathVariable Long id, @RequestBody ReservationDto dto) {
        return reservationService.updateReservation(id, dto);
    }

    // CHECK-IN
    @PutMapping("/{id}/checkin")
    public ReservationDto checkIn(@PathVariable Long id) {
        ReservationDto dto = reservationService.getReservationById(id);
        dto.setStatus(ReservationStatus.CHECKED_IN);
        dto.setActualCheckIn(LocalDateTime.now());
        return reservationService.updateReservation(id, dto);
    }

    // CHECK-OUT
    @PutMapping("/{id}/checkout")
    public ReservationDto checkOut(@PathVariable Long id) {
        ReservationDto dto = reservationService.getReservationById(id);
        dto.setStatus(ReservationStatus.CHECKED_OUT);
        dto.setActualCheckOut(LocalDateTime.now());
        return reservationService.updateReservation(id, dto);
    }

    @PutMapping("/{id}/cancel")
    public void cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
    }
}