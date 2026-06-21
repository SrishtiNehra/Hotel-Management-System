package com.hotel.Hotel_Reservation_Management.serviceImpl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.Hotel_Reservation_Management.dto.ReservationDTO;
import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.entity.Customer;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;
import com.hotel.Hotel_Reservation_Management.entity.Room;
import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;
import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import com.hotel.Hotel_Reservation_Management.enums.RoomStatus;
import com.hotel.Hotel_Reservation_Management.exception.ResourceNotFoundException;
import com.hotel.Hotel_Reservation_Management.mapper.ReservationMapper;
import com.hotel.Hotel_Reservation_Management.repository.BillingRepository;
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

    @Autowired
    private BillingRepository billingRepository;

    @Override
    public ReservationDTO createReservation(ReservationDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + dto.getCustomerId()
                ));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Room not found with id: " + dto.getRoomId()
                ));

        if (!room.getStatus().equals(RoomStatus.AVAILABLE)) {
            throw new RuntimeException("Room not available");
        }

        Reservation reservation = ReservationMapper.toEntity(dto, customer, room);
        reservation.setStatus(ReservationStatus.BOOKED);

        room.setStatus(RoomStatus.RESERVED);
        roomRepository.save(room);

        Reservation saved = reservationRepository.save(reservation);

        return ReservationMapper.toDTO(saved);
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        return ReservationMapper.toDTO(
                reservationRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Reservation not found with id: " + id
                        ))
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
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation not found with id: " + id
                ));

        reservation.setPlannedCheckIn(dto.getPlannedCheckIn());
        reservation.setPlannedCheckOut(dto.getPlannedCheckOut());
        reservation.setStatus(dto.getStatus());

        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Override
    public void cancelReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation not found with id: " + id
                ));

        reservation.setStatus(ReservationStatus.CANCELLED);

        Room room = reservation.getRoom();
        room.setStatus(RoomStatus.AVAILABLE);

        reservationRepository.save(reservation);
        roomRepository.save(room);
    }

    @Override
    public void checkout(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation not found with id: " + reservationId
                ));

        reservation.setStatus(ReservationStatus.COMPLETED);

        Room room = reservation.getRoom();

        long days = java.time.temporal.ChronoUnit.DAYS.between(
                reservation.getPlannedCheckIn(),
                reservation.getPlannedCheckOut()
        );

        if (days <= 0) days = 1;

        BigDecimal amount = room.getPricePerNight()
                .multiply(BigDecimal.valueOf(days));

        Billing billing = new Billing();
        billing.setReservation(reservation);
        billing.setAmount(amount);
        billing.setPaymentStatus(PaymentStatus.PENDING);
        billing.setPaymentDate(java.time.LocalDate.now());

        billingRepository.save(billing);

        room.setStatus(RoomStatus.AVAILABLE);

        reservationRepository.save(reservation);
        roomRepository.save(room);
    }

    @Override
    public void deleteReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation not found with id: " + id
                ));

        Room room = reservation.getRoom();

        if (reservation.getStatus() != ReservationStatus.CANCELLED) {
            room.setStatus(RoomStatus.AVAILABLE);
        }

        reservationRepository.delete(reservation);
        roomRepository.save(room);
    }

    @Override
    public List<ReservationDTO> getReservationsByLoggedInUser(Authentication authentication) {

        String username = authentication.getName();

        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with username: " + username
                ));

        return reservationRepository
                .findByCustomer_CustomerId(customer.getCustomerId())
                .stream()
                .map(ReservationMapper::toDTO)
                .toList();
    }
}