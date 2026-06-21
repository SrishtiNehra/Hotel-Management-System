package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.HotelDTO;
import com.hotel.Hotel_Reservation_Management.entity.Hotel;
import com.hotel.Hotel_Reservation_Management.exception.ResourceNotFoundException;
import com.hotel.Hotel_Reservation_Management.mapper.HotelMapper;
import com.hotel.Hotel_Reservation_Management.repository.HotelRepository;
import com.hotel.Hotel_Reservation_Management.service.HotelService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    @Autowired
    private final HotelRepository hotelRepository;

    @Override
    public HotelDTO createHotel(HotelDTO dto) {
        return HotelMapper.toDTO(
                hotelRepository.save(HotelMapper.toEntity(dto))
        );
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        return HotelMapper.toDTO(
                hotelRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Hotel not found with id: " + id
                        ))
        );
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO dto) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hotel not found with id: " + id
                ));

        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setCity(dto.getCity());
        hotel.setState(dto.getState());
        hotel.setZipCode(dto.getZipCode());
        hotel.setContactNumber(dto.getContactNumber());
        hotel.setEmail(dto.getEmail());

        return HotelMapper.toDTO(hotelRepository.save(hotel));
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}