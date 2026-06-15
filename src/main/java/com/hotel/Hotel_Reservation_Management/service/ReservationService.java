package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDTO;
import java.util.List;

public interface ReservationService {

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    ReservationDTO getReservationById(Long id);

    List<ReservationDTO> getAllReservations();

    List<ReservationDTO> getReservationsByCustomer(Long customerId);

    ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);

    void cancelReservation(Long id);
}