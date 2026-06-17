package com.hotel.Hotel_Reservation_Management.dto;

import java.math.BigDecimal;

import com.hotel.Hotel_Reservation_Management.enums.RoomStatus;
import com.hotel.Hotel_Reservation_Management.enums.RoomType;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDTO {

    private Long roomId;

    @NotBlank
    private String roomNumber;

    @NotNull
    private RoomType roomType;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull
    private RoomStatus status;

    @NotNull
    private Long hotelId;
}