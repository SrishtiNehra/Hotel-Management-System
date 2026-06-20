package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.RoomDTO;
import com.hotel.Hotel_Reservation_Management.entity.Hotel;
import com.hotel.Hotel_Reservation_Management.entity.Room;
import com.hotel.Hotel_Reservation_Management.enums.RoomStatus;
import com.hotel.Hotel_Reservation_Management.enums.RoomType;
import com.hotel.Hotel_Reservation_Management.mapper.RoomMapper;
import com.hotel.Hotel_Reservation_Management.repository.HotelRepository;
import com.hotel.Hotel_Reservation_Management.repository.RoomRepository;
import com.hotel.Hotel_Reservation_Management.serviceImpl.RoomServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Room room;
    private RoomDTO roomDTO;
    private Hotel hotel;

    @BeforeEach
    void setUp() {

        hotel = new Hotel();
        hotel.setHotelId(1L);

        room = new Room();
        room.setRoomId(1L);
        room.setRoomNumber("101");
        room.setRoomType(RoomType.DELUXE);
        room.setPrice(BigDecimal.valueOf(2500));
        room.setStatus(RoomStatus.AVAILABLE);
        room.setHotel(hotel);

        roomDTO = new RoomDTO();
        roomDTO.setHotelId(1L);
        roomDTO.setRoomNumber("101");
        roomDTO.setRoomType(RoomType.DELUXE);
        roomDTO.setPrice(BigDecimal.valueOf(2500));
        roomDTO.setStatus(RoomStatus.AVAILABLE);
    }

    @Test
    void testAddRoom() {

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        try (MockedStatic<RoomMapper> mapper = mockStatic(RoomMapper.class)) {

            mapper.when(() -> RoomMapper.toEntity(any(RoomDTO.class), any(Hotel.class)))
                    .thenReturn(room);

            mapper.when(() -> RoomMapper.toDTO(any(Room.class)))
                    .thenReturn(roomDTO);

            RoomDTO result = roomService.addRoom(roomDTO);

            assertNotNull(result);
            assertEquals("101", result.getRoomNumber());

            verify(roomRepository, times(1)).save(any(Room.class));
        }
    }

    @Test
    void testGetRoomById() {

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        try (MockedStatic<RoomMapper> mapper = mockStatic(RoomMapper.class)) {
            mapper.when(() -> RoomMapper.toDTO(room)).thenReturn(roomDTO);

            RoomDTO result = roomService.getRoomById(1L);

            assertEquals("101", result.getRoomNumber());
        }
    }

    @Test
    void testGetAllRooms() {

        when(roomRepository.findAll()).thenReturn(List.of(room));

        try (MockedStatic<RoomMapper> mapper = mockStatic(RoomMapper.class)) {
            mapper.when(() -> RoomMapper.toDTO(any(Room.class))).thenReturn(roomDTO);

            List<RoomDTO> result = roomService.getAllRooms();

            assertEquals(1, result.size());
        }
    }

    @Test
    void testGetRoomsByHotel() {

        when(roomRepository.findByHotel_HotelId(1L)).thenReturn(List.of(room));

        try (MockedStatic<RoomMapper> mapper = mockStatic(RoomMapper.class)) {
            mapper.when(() -> RoomMapper.toDTO(any(Room.class))).thenReturn(roomDTO);

            List<RoomDTO> result = roomService.getRoomsByHotel(1L);

            assertEquals(1, result.size());
        }
    }

    @Test
    void testUpdateRoom() {

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        try (MockedStatic<RoomMapper> mapper = mockStatic(RoomMapper.class)) {
            mapper.when(() -> RoomMapper.toDTO(any(Room.class))).thenReturn(roomDTO);

            RoomDTO result = roomService.updateRoom(1L, roomDTO);

            assertEquals("101", result.getRoomNumber());
            verify(roomRepository).save(room);
        }
    }

    @Test
    void testDeleteRoom() {

        doNothing().when(roomRepository).deleteById(1L);

        roomService.deleteRoom(1L);

        verify(roomRepository, times(1)).deleteById(1L);
    }
}