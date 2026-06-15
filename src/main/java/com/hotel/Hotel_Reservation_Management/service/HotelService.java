package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.HotelDTO;
import java.util.List;

public interface HotelService {

    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long id);

    List<HotelDTO> getAllHotels();

    HotelDTO updateHotel(Long id, HotelDTO hotelDTO);

    void deleteHotel(Long id);
}