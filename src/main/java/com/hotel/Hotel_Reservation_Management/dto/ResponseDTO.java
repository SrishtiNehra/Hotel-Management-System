package com.hotel.Hotel_Reservation_Management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
	
    private String token;
    private String role;
    private String username;
}