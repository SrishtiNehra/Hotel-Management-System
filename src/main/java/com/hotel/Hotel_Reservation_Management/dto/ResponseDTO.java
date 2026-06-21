package com.hotel.Hotel_Reservation_Management.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDTO {
	
    private String token;
    private String role;
    private String username;
    private Long customerId;
    
    

}