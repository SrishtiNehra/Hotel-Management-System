package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.HotelDto;
import com.hotel.Hotel_Reservation_Management.entity.Hotel;

public class HotelMapper {

    public static HotelDto toDto(Hotel hotel) {
        if (hotel == null) return null;

        HotelDto dto = new HotelDto();
        dto.setHotelId(hotel.getHotelId());
        dto.setName(hotel.getName());
        dto.setAddress(hotel.getAddress());
        dto.setCity(hotel.getCity());
        dto.setState(hotel.getState());
        dto.setZipCode(hotel.getZipCode());
        dto.setContactNumber(hotel.getContactNumber());
        dto.setEmail(hotel.getEmail());

        return dto;
    }

    public static Hotel toEntity(HotelDto dto) {
        if (dto == null) return null;

        Hotel hotel = new Hotel();
        hotel.setHotelId(dto.getHotelId());
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setCity(dto.getCity());
        hotel.setState(dto.getState());
        hotel.setZipCode(dto.getZipCode());
        hotel.setContactNumber(dto.getContactNumber());
        hotel.setEmail(dto.getEmail());

        return hotel;
    }
}