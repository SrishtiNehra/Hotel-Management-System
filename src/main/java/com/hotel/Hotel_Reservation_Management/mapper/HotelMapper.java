package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.HotelDTO;
import com.hotel.Hotel_Reservation_Management.entity.Hotel;

public class HotelMapper {

    public static Hotel toEntity(HotelDTO dto) {
        Hotel h = new Hotel();

        h.setHotelId(dto.getHotelId());
        h.setName(dto.getName());
        h.setAddress(dto.getAddress());
        h.setCity(dto.getCity());
        h.setState(dto.getState());
        h.setZipCode(dto.getZipCode());
        h.setContactNumber(dto.getContactNumber());
        h.setEmail(dto.getEmail());

        return h;
    }

    public static HotelDTO toDTO(Hotel h) {
        HotelDTO dto = new HotelDTO();

        dto.setHotelId(h.getHotelId());
        dto.setName(h.getName());
        dto.setAddress(h.getAddress());
        dto.setCity(h.getCity());
        dto.setState(h.getState());
        dto.setZipCode(h.getZipCode());
        dto.setContactNumber(h.getContactNumber());
        dto.setEmail(h.getEmail());

        return dto;
    }
}