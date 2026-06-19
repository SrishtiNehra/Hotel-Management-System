package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import com.hotel.Hotel_Reservation_Management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

        // ✅ FIX: revenue instead of count
        BigDecimal revenue = billingRepository.sumAllRevenue();
        map.put("totalBills", revenue != null ? revenue : BigDecimal.ZERO);

        // ✅ FIX: DB-level filtering instead of Java stream
        long activeReservations =
                reservationRepository.countByStatus(ReservationStatus.BOOKED);

        map.put("activeReservations", activeReservations);

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
                reservationRepository.countByCustomer_CustomerId(customerId));

        map.put("myBills",
                billingRepository.findByCustomerId(customerId));

        return map;
    }
}