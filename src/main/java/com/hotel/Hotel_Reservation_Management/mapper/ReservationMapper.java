package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDto;
import com.hotel.Hotel_Reservation_Management.entity.Customer;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.entity.Room;

public class ReservationMapper {

    public static ReservationDto toDto(Reservation reservation) {
        if (reservation == null) return null;

        ReservationDto dto = new ReservationDto();
        dto.setReservationId(reservation.getReservationId());
        dto.setBookingDate(reservation.getBookingDate());
        dto.setPlannedCheckIn(reservation.getPlannedCheckIn());
        dto.setPlannedCheckOut(reservation.getPlannedCheckOut());
        dto.setActualCheckIn(reservation.getActualCheckIn());
        dto.setActualCheckOut(reservation.getActualCheckOut());
        dto.setStatus(reservation.getStatus());

        if (reservation.getCustomer() != null)
            dto.setCustomerId(reservation.getCustomer().getCustomerId());

        if (reservation.getRoom() != null)
            dto.setRoomId(reservation.getRoom().getRoomId());

        return dto;
    }

    public static Reservation toEntity(ReservationDto dto, Customer customer, Room room) {
        if (dto == null) return null;

        Reservation reservation = new Reservation();
        reservation.setReservationId(dto.getReservationId());
        reservation.setBookingDate(dto.getBookingDate());
        reservation.setPlannedCheckIn(dto.getPlannedCheckIn());
        reservation.setPlannedCheckOut(dto.getPlannedCheckOut());
        reservation.setActualCheckIn(dto.getActualCheckIn());
        reservation.setActualCheckOut(dto.getActualCheckOut());
        reservation.setStatus(dto.getStatus());

        reservation.setCustomer(customer);
        reservation.setRoom(room);

        return reservation;
    }
}