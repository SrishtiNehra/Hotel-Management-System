package com.hotel.Hotel_Reservation_Management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}