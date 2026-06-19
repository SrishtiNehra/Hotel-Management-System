package com.hotel.Hotel_Reservation_Management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerPageController {

    // ================= DASHBOARD =================
    @GetMapping("/dashboard")
    public String customerDashboard() {
        return "customer/customer-dashboard";
    }

    // ================= PROFILE =================
    @GetMapping("/profile")
    public String profilePage() {
        return "customer/profile";
    }

    @GetMapping("/edit-profile")
    public String editProfilePage() {
        return "customer/edit-profile";
    }

    // ================= BOOKINGS =================
    @GetMapping("/bookings")
    public String myBookingsPage() {
        return "customer/bookings";
    }

    // ================= BILLS =================
    @GetMapping("/bills")
    public String myBillsPage() {
        return "customer/bills";
    }

    // ================= RESERVATION =================
    @GetMapping("/create-reservation")
    public String createReservationPage() {
        return "customer/create-reservation";
    }

    // ================= ROOMS =================
    @GetMapping("/rooms")
    public String roomBookingPage() {
        return "customer/rooms";
    }

    // ================= PAYMENT =================
    @GetMapping("/payment")
    public String paymentPage() {
        return "customer/payment";
    }

    // ================= CHECK-IN / CHECK-OUT HISTORY =================
    @GetMapping("/checkin-history")
    public String checkinHistoryPage() {
        return "customer/checkin-history";
    }
}