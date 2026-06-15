package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDto;
import java.util.List;

public interface ReservationService {

    ReservationDto createReservation(ReservationDto reservationDto);

    ReservationDto getReservationById(Long id);

    List<ReservationDto> getAllReservations();

    List<ReservationDto> getReservationsByCustomer(Long customerId);

    ReservationDto updateReservation(Long id, ReservationDto reservationDto);

    void cancelReservation(Long id);
}