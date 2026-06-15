package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.RoomDTO;
import com.hotel.Hotel_Reservation_Management.service.RoomService;
import com.hotel.Hotel_Reservation_Management.validator.RoomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomValidator roomValidator;

    @PostMapping
    public RoomDTO create(@RequestBody RoomDTO dto) {
        roomValidator.validate(dto);
        return roomService.addRoom(dto);
    }

    @GetMapping("/{id}")
    public RoomDTO getById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping
    public List<RoomDTO> getAll() {
        return roomService.getAllRooms();
    }

    @GetMapping("/hotel/{hotelId}")
    public List<RoomDTO> getByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId);
    }

    @PutMapping("/{id}")
    public RoomDTO update(@PathVariable Long id, @RequestBody RoomDTO dto) {
        return roomService.updateRoom(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}