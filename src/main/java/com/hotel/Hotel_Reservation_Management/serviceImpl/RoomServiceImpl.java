package com.hotel.Hotel_Reservation_Management.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.Hotel_Reservation_Management.dto.RoomDTO;
import com.hotel.Hotel_Reservation_Management.entity.Hotel;
import com.hotel.Hotel_Reservation_Management.entity.Room;
import com.hotel.Hotel_Reservation_Management.mapper.RoomMapper;
import com.hotel.Hotel_Reservation_Management.repository.HotelRepository;
import com.hotel.Hotel_Reservation_Management.repository.RoomRepository;
import com.hotel.Hotel_Reservation_Management.service.RoomService;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	@Autowired
    private final RoomRepository roomRepository;
	
	@Autowired
    private final HotelRepository hotelRepository;

    @Override
    public RoomDTO addRoom(RoomDTO dto) {

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        Room room = RoomMapper.toEntity(dto, hotel);

        return RoomMapper.toDTO(roomRepository.save(room));
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        return RoomMapper.toDTO(
                roomRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Room not found"))
        );
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getRoomsByHotel(Long hotelId) {
        return roomRepository.findByHotel_HotelId(hotelId)
                .stream()
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO dto) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPrice(dto.getPrice());
        room.setStatus(dto.getStatus());

        return RoomMapper.toDTO(roomRepository.save(room));
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}