package com.hotel.Hotel_Reservation_Management.entity;



import com.hotel.Hotel_Reservation_Management.enums.Role;
import com.hotel.Hotel_Reservation_Management.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class User {

    @Column(nullable = false, unique = true)
    protected String username;

    @Column(nullable = false)
    protected String password;

    @Enumerated(EnumType.STRING)
    protected Role role;

    @Enumerated(EnumType.STRING)
    protected UserStatus status;

}