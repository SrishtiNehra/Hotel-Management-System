package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BillingRepository billingRepository;

    // =========================
    // ADMIN DASHBOARD
    // =========================
    @GetMapping("/admin")
    public Map<String, Object> adminDashboard() {

        Map<String, Object> map = new HashMap<>();

        map.put("totalCustomers", customerRepository.count());
        map.put("totalHotels", hotelRepository.count());
        map.put("totalRooms", roomRepository.count());
        map.put("totalReservations", reservationRepository.count());
        map.put("totalBills", billingRepository.count());

        map.put("activeReservations",
                reservationRepository.findAll()
                        .stream()
                        .filter(r -> r.getStatus().name().equals("BOOKED"))
                        .count()
        );

        return map;
    }

    // =========================
    // CUSTOMER DASHBOARD
    // =========================
    @GetMapping("/customer/{customerId}")
    public Map<String, Object> customerDashboard(@PathVariable Long customerId) {

        Map<String, Object> map = new HashMap<>();

        map.put("myReservations",
                reservationRepository.findByCustomer_CustomerId(customerId));

        map.put("totalMyReservations",
                reservationRepository.findByCustomer_CustomerId(customerId).size());

        map.put("myBills",
                billingRepository.findAll()
                        .stream()
                        .filter(b -> b.getReservation()
                                .getCustomer()
                                .getCustomerId()
                                .equals(customerId))
                        .toList()
        );

        return map;
    }
}