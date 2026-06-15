package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.RoomDTO;
import java.util.List;

public interface RoomService {

    RoomDTO addRoom(RoomDTO roomDTO);

    RoomDTO getRoomById(Long id);

    List<RoomDTO> getAllRooms();

    List<RoomDTO> getRoomsByHotel(Long hotelId);

    RoomDTO updateRoom(Long id, RoomDTO roomDTO);

    void deleteRoom(Long id);
}