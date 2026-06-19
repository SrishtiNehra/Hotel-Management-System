package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDTO;
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
    public ReservationDTO create(@RequestBody ReservationDTO dto) {
        reservationValidator.validate(dto);
        return reservationService.createReservation(dto);
    }

    @GetMapping("/{id}")
    public ReservationDTO getById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping
    public List<ReservationDTO> getAll() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/customer/{customerId}")
    public List<ReservationDTO> getByCustomer(@PathVariable Long customerId) {
        return reservationService.getReservationsByCustomer(customerId);
    }

    @PutMapping("/{id}")
    public ReservationDTO update(@PathVariable Long id, @RequestBody ReservationDTO dto) {
        return reservationService.updateReservation(id, dto);
    }

    // CHECK-IN
    @PutMapping("/{id}/checkin")
    public ReservationDTO checkIn(@PathVariable Long id) {
        ReservationDTO dto = reservationService.getReservationById(id);
        dto.setStatus(ReservationStatus.CHECKED_IN);
        
        return reservationService.updateReservation(id, dto);
    }

    // CHECK-OUT
    @PutMapping("/{id}/checkout")
    public ReservationDTO checkOut(@PathVariable Long id) {
        ReservationDTO dto = reservationService.getReservationById(id);
        dto.setStatus(ReservationStatus.CHECKED_OUT);
        
        return reservationService.updateReservation(id, dto);
    }

    @PutMapping("/{id}/cancel")
    public void cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
    }
}