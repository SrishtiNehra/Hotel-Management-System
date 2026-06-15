package com.hotel.Hotel_Reservation_Management.mapper;


import com.hotel.Hotel_Reservation_Management.dto.RoomDto;
import com.hotel.Hotel_Reservation_Management.entity.Hotel;
import com.hotel.Hotel_Reservation_Management.entity.Room;

public class RoomMapper {

    public static RoomDto toDto(Room room) {
        if (room == null) return null;

        RoomDto dto = new RoomDto();
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

    public static Room toEntity(RoomDto dto, Hotel hotel) {
        if (dto == null) return null;

        Room room = new Room();
        room.setRoomId(dto.getRoomId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPrice(dto.getPrice());
        room.setStatus(dto.getStatus());
        room.setHotel(hotel); // important relation mapping

        return room;
    }
}