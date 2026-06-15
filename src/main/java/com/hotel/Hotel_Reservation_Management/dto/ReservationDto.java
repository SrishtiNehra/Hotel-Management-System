package com.hotel.Hotel_Reservation_Management.dto;

import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationDto {

    private Long reservationId;

    @NotNull
    private LocalDate bookingDate;

    @NotNull
    private LocalDate plannedCheckIn;

    @NotNull
    private LocalDate plannedCheckOut;

    private LocalDateTime actualCheckIn;
    private LocalDateTime actualCheckOut;

    @NotNull
    private ReservationStatus status;

    @NotNull
    private Long customerId;

    @NotNull
    private Long roomId;
}