package com.hotel.Hotel_Reservation_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.Hotel_Reservation_Management.entity.Room;
import com.hotel.Hotel_Reservation_Management.enums.RoomStatus;
import com.hotel.Hotel_Reservation_Management.enums.RoomType;

import java.math.BigDecimal;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotel_HotelId(Long hotelId);

    List<Room> findByStatus(RoomStatus status);

    List<Room> findByRoomType(RoomType roomType);

    List<Room> findByPriceLessThanEqual(BigDecimal price);
}