package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.RoomDTO;
import com.hotel.Hotel_Reservation_Management.entity.Hotel;
import com.hotel.Hotel_Reservation_Management.entity.Room;

public class RoomMapper {

    public static Room toEntity(RoomDTO dto, Hotel hotel) {
        Room room = new Room();

        room.setRoomId(dto.getRoomId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPrice(dto.getPrice());
        room.setStatus(dto.getStatus());
        room.setHotel(hotel);

        return room;
    }

    public static RoomDTO toDTO(Room room) {
        RoomDTO dto = new RoomDTO();

        dto.setRoomId(room.getRoomId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setRoomType(room.getRoomType());
        dto.setPrice(room.getPrice());
        dto.setStatus(room.getStatus());

        if (room.getHotel() != null) {
            dto.setHotelId(room.getHotel().getHotelId());
        }

        return dto;
    }
}