package com.hotel.Hotel_Reservation_Management.entity;

import java.math.BigDecimal;
import java.util.List;

import com.hotel.Hotel_Reservation_Management.enums.RoomStatus;
import com.hotel.Hotel_Reservation_Management.enums.RoomType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(unique = true)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

}