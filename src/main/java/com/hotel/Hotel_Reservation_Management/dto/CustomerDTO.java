package com.hotel.Hotel_Reservation_Management.dto;

import com.hotel.Hotel_Reservation_Management.enums.Role;
import com.hotel.Hotel_Reservation_Management.enums.UserStatus;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long customerId;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String fullName;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    private Role role;

    private UserStatus status;
}