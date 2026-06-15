package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.HotelDto;
import com.hotel.Hotel_Reservation_Management.entity.Hotel;
import com.hotel.Hotel_Reservation_Management.mapper.HotelMapper;
import com.hotel.Hotel_Reservation_Management.repository.HotelRepository;
import com.hotel.Hotel_Reservation_Management.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelDto createHotel(HotelDto dto) {
        Hotel hotel = HotelMapper.toEntity(dto);
        return HotelMapper.toDto(hotelRepository.save(hotel));
    }

    @Override
    public HotelDto getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return HotelMapper.toDto(hotel);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDto updateHotel(Long id, HotelDto dto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setCity(dto.getCity());
        hotel.setState(dto.getState());
        hotel.setZipCode(dto.getZipCode());
        hotel.setContactNumber(dto.getContactNumber());
        hotel.setEmail(dto.getEmail());

        return HotelMapper.toDto(hotelRepository.save(hotel));
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}