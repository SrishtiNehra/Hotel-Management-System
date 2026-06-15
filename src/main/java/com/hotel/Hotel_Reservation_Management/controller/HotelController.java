package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.HotelDTO;
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
    public HotelDTO create(@RequestBody HotelDTO dto) {
        hotelValidator.validate(dto);
        return hotelService.createHotel(dto);
    }

    @GetMapping("/{id}")
    public HotelDTO getById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping
    public List<HotelDTO> getAll() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public HotelDTO update(@PathVariable Long id, @RequestBody HotelDTO dto) {
        hotelValidator.validate(dto);
        return hotelService.updateHotel(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}