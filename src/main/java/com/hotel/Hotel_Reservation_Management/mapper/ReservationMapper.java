package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDTO;
import com.hotel.Hotel_Reservation_Management.entity.Customer;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.entity.Room;

public class ReservationMapper {

    public static Reservation toEntity(
            ReservationDTO dto,
            Customer customer,
            Room room
    ) {
        Reservation r = new Reservation();

        r.setReservationId(dto.getReservationId());
        r.setBookingDate(dto.getBookingDate());
        r.setPlannedCheckIn(dto.getPlannedCheckIn());
        r.setPlannedCheckOut(dto.getPlannedCheckOut());
        r.setStatus(dto.getStatus());

        r.setCustomer(customer);
        r.setRoom(room);
       
        

        return r;
    }

    public static ReservationDTO toDTO(Reservation r) {
        ReservationDTO dto = new ReservationDTO();

        dto.setReservationId(r.getReservationId());
        dto.setBookingDate(r.getBookingDate());
        dto.setPlannedCheckIn(r.getPlannedCheckIn());
        dto.setPlannedCheckOut(r.getPlannedCheckOut());
        dto.setRoomPrice(r.getRoom().getPrice());
       
        dto.setStatus(r.getStatus());

        if (r.getCustomer() != null)
            dto.setCustomerId(r.getCustomer().getCustomerId());

        if (r.getRoom() != null)
            dto.setRoomId(r.getRoom().getRoomId());

        return dto;
    }
}