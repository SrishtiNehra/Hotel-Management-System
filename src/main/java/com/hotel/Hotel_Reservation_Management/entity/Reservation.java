package com.hotel.Hotel_Reservation_Management.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private LocalDate bookingDate;

    private LocalDate plannedCheckIn;

    private LocalDate plannedCheckOut;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(mappedBy = "reservation",
            cascade = CascadeType.ALL)
    private Billing billing;

	public void setActualCheckOut(LocalDate now) {
		// TODO Auto-generated method stub
		
	}

}