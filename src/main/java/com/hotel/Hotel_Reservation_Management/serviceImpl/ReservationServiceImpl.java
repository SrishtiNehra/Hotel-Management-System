package com.hotel.Hotel_Reservation_Management.serviceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDTO;
import com.hotel.Hotel_Reservation_Management.entity.Customer;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.entity.Room;
import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import com.hotel.Hotel_Reservation_Management.enums.RoomStatus;
import com.hotel.Hotel_Reservation_Management.mapper.ReservationMapper;
import com.hotel.Hotel_Reservation_Management.repository.CustomerRepository;
import com.hotel.Hotel_Reservation_Management.repository.ReservationRepository;
import com.hotel.Hotel_Reservation_Management.repository.RoomRepository;
import com.hotel.Hotel_Reservation_Management.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	@Autowired
    private final ReservationRepository reservationRepository;
	
	@Autowired
    private final CustomerRepository customerRepository;
	
	@Autowired
    private final RoomRepository roomRepository;

    @Override
    public ReservationDTO createReservation(ReservationDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.getStatus().name().equals("AVAILABLE")) {
            throw new RuntimeException("Room not available");
        }

        Reservation reservation = ReservationMapper.toEntity(dto, customer, room);
        reservation.setStatus(ReservationStatus.BOOKED);

        room.setStatus(RoomStatus.AVAILABLE);
        roomRepository.save(room);

        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        return ReservationMapper.toDTO(
                reservationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Reservation not found"))
        );
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationsByCustomer(Long customerId) {
        return reservationRepository.findByCustomer_CustomerId(customerId)
                .stream()
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO dto) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setPlannedCheckIn(dto.getPlannedCheckIn());
        reservation.setPlannedCheckOut(dto.getPlannedCheckOut());
        reservation.setStatus(dto.getStatus());

        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Override
    public void cancelReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CANCELLED);

        Room room = reservation.getRoom();
        room.setStatus(RoomStatus.AVAILABLE);

        reservationRepository.save(reservation);
        roomRepository.save(room);
    }
}