package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.RoomDto;
import java.util.List;

public interface RoomService {

    RoomDto addRoom(RoomDto roomDto);

    RoomDto getRoomById(Long id);

    List<RoomDto> getAllRooms();

    List<RoomDto> getRoomsByHotel(Long hotelId);

    RoomDto updateRoom(Long id, RoomDto roomDto);

    void deleteRoom(Long id);
}