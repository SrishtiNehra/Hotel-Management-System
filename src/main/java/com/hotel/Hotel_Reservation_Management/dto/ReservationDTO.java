package com.hotel.Hotel_Reservation_Management.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {

    private Long reservationId;

    @NotNull
    private LocalDate bookingDate;

    @NotNull
    private LocalDate plannedCheckIn;

    @NotNull
    private LocalDate plannedCheckOut;

    

    private ReservationStatus status;

    @NotNull
    private Long customerId;

    @NotNull
    private Long roomId;
}