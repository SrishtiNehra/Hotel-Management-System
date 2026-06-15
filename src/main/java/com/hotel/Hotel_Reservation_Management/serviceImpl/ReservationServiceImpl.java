package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDto;
import com.hotel.Hotel_Reservation_Management.entity.Customer;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.entity.Room;
import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import com.hotel.Hotel_Reservation_Management.mapper.ReservationMapper;
import com.hotel.Hotel_Reservation_Management.repository.CustomerRepository;
import com.hotel.Hotel_Reservation_Management.repository.ReservationRepository;
import com.hotel.Hotel_Reservation_Management.repository.RoomRepository;
import com.hotel.Hotel_Reservation_Management.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public ReservationDto createReservation(ReservationDto dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Optional: check room availability
        if (room.getStatus() != null &&
                !room.getStatus().name().equals("AVAILABLE")) {
            throw new RuntimeException("Room is not available");
        }

        Reservation reservation = ReservationMapper.toEntity(dto, customer, room);

        reservation.setStatus(ReservationStatus.BOOKED);

        // mark room as reserved
        room.setStatus(com.hotel.Hotel_Reservation_Management.enums.RoomStatus.RESERVED);

        roomRepository.save(room);

        return ReservationMapper.toDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        return ReservationMapper.toDto(reservation);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> getReservationsByCustomer(Long customerId) {
        return reservationRepository.findByCustomer_CustomerId(customerId)
                .stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto updateReservation(Long id, ReservationDto dto) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setPlannedCheckIn(dto.getPlannedCheckIn());
        reservation.setPlannedCheckOut(dto.getPlannedCheckOut());
        reservation.setStatus(dto.getStatus());

        return ReservationMapper.toDto(reservationRepository.save(reservation));
    }

    @Override
    public void cancelReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CANCELLED);

        // free the room
        Room room = reservation.getRoom();
        room.setStatus(com.hotel.Hotel_Reservation_Management.enums.RoomStatus.AVAILABLE);

        reservationRepository.save(reservation);
        roomRepository.save(room);
    }
}