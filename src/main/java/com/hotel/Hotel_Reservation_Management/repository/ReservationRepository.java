package com.hotel.Hotel_Reservation_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCustomer_CustomerId(Long customerId);

    List<Reservation> findByRoom_RoomId(Long roomId);

    List<Reservation> findByStatus(ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.plannedCheckIn = :plannedCheckIn")
    List<Reservation> findByPlannedCheckIn(
            @Param("plannedCheckIn") LocalDate plannedCheckIn);

    @Query("SELECT r FROM Reservation r WHERE r.plannedCheckOut = :plannedCheckOut")
    List<Reservation> findByPlannedCheckOut(
            @Param("plannedCheckOut") LocalDate plannedCheckOut);
}