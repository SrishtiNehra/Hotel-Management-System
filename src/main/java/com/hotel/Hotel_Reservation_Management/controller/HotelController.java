package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.HotelDto;
import com.hotel.Hotel_Reservation_Management.service.HotelService;
import com.hotel.Hotel_Reservation_Management.validator.HotelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelValidator hotelValidator;

    @PostMapping
    public HotelDto create(@RequestBody HotelDto dto) {
        hotelValidator.validate(dto);
        return hotelService.createHotel(dto);
    }

    @GetMapping("/{id}")
    public HotelDto getById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping
    public List<HotelDto> getAll() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public HotelDto update(@PathVariable Long id, @RequestBody HotelDto dto) {
        hotelValidator.validate(dto);
        return hotelService.updateHotel(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}