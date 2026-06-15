package com.hotel.Hotel_Reservation_Management.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private String contactNumber;

    private String email;

    @OneToMany(mappedBy = "hotel",
            cascade = CascadeType.ALL)
    private List<Room> rooms;

}