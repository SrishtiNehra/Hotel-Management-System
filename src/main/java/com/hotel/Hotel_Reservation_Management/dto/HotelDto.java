package com.hotel.Hotel_Reservation_Management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDto {

    private Long hotelId;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String zipCode;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String contactNumber;

    @NotBlank
    @Email
    private String email;
}