package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.RoomDTO;
import com.hotel.Hotel_Reservation_Management.enums.RoomStatus;
import com.hotel.Hotel_Reservation_Management.enums.RoomType;
import com.hotel.Hotel_Reservation_Management.service.RoomService;
import com.hotel.Hotel_Reservation_Management.validator.RoomValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @Mock
    private RoomValidator roomValidator;

    @InjectMocks
    private RoomController roomController;

    @Test
    void testCreate() {

        RoomDTO dto = new RoomDTO();
        dto.setRoomNumber("101");
        dto.setRoomType(RoomType.DELUXE);
        dto.setPrice(BigDecimal.valueOf(2500));
        dto.setStatus(RoomStatus.AVAILABLE);

        when(roomService.addRoom(dto)).thenReturn(dto);

        RoomDTO result = roomController.create(dto);

        assertEquals("101", result.getRoomNumber());

        verify(roomValidator, times(1)).validate(dto);
        verify(roomService, times(1)).addRoom(dto);
    }

    @Test
    void testGetById() {

        RoomDTO dto = new RoomDTO();
        dto.setRoomNumber("101");

        when(roomService.getRoomById(1L)).thenReturn(dto);

        RoomDTO result = roomController.getById(1L);

        assertEquals("101", result.getRoomNumber());
    }

    @Test
    void testGetAll() {

        when(roomService.getAllRooms()).thenReturn(List.of(new RoomDTO()));

        List<RoomDTO> result = roomController.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void testGetByHotel() {

        when(roomService.getRoomsByHotel(1L)).thenReturn(List.of(new RoomDTO()));

        List<RoomDTO> result = roomController.getByHotel(1L);

        assertEquals(1, result.size());
    }

    @Test
    void testUpdate() {

        RoomDTO dto = new RoomDTO();
        dto.setRoomNumber("101");

        when(roomService.updateRoom(1L, dto)).thenReturn(dto);

        RoomDTO result = roomController.update(1L, dto);

        assertEquals("101", result.getRoomNumber());
    }

    @Test
    void testDelete() {

        doNothing().when(roomService).deleteRoom(1L);

        roomController.delete(1L);

        verify(roomService, times(1)).deleteRoom(1L);
    }
}