package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.entity.Customer;
import com.hotel.Hotel_Reservation_Management.enums.ReservationStatus;
import com.hotel.Hotel_Reservation_Management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        BigDecimal revenue = billingRepository.sumAllRevenue();
        map.put("totalBills", revenue != null ? revenue : BigDecimal.ZERO);

        long activeReservations =
                reservationRepository.countByStatus(ReservationStatus.BOOKED);

        map.put("activeReservations", activeReservations);

        return map;
    }

    // =========================
    // CUSTOMER DASHBOARD (IMPROVED)
    // =========================
    @GetMapping("/customer")
    public Map<String, Object> customerDashboard(Authentication authentication) {

        String username = authentication.getName();

        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Long customerId = customer.getCustomerId();

        Map<String, Object> map = new HashMap<>();

        long totalReservations =
                reservationRepository.countByCustomer_CustomerId(customerId);

        long activeBookings =
                reservationRepository.countByCustomer_CustomerIdAndStatus(
                        customerId,
                        ReservationStatus.BOOKED
                );

        BigDecimal totalBills =
                Optional.ofNullable(billingRepository.sumByCustomerId(customerId))
                        .orElse(BigDecimal.ZERO);

        BigDecimal pendingPayments =
                Optional.ofNullable(billingRepository.sumPendingByCustomerId(customerId))
                        .orElse(BigDecimal.ZERO);

        map.put("totalReservations", totalReservations);
        map.put("activeBookings", activeBookings);
        map.put("totalBills", totalBills);
        map.put("pendingPayments", pendingPayments);

        return map;
    }
}