package com.hotel.Hotel_Reservation_Management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/admin/profile")
    public String adminProfile() {
        return "admin/profile";
    }

    @GetMapping("/admin/customers")
    public String customers() {
        return "admin/customers";
    }

    @GetMapping("/admin/hotels")
    public String hotels() {
        return "admin/hotels";
    }

    @GetMapping("/admin/rooms")
    public String rooms() {
        return "admin/rooms";
    }

    @GetMapping("/admin/reservations")
    public String reservations() {
        return "admin/reservations";
    }

    @GetMapping("/admin/billing")
    public String billing() {
        return "admin/billing";
    }

    @GetMapping("/admin/reports")
    public String reports() {
        return "admin/reports";
    }

    @GetMapping("/admin/checkin-checkout")
    public String checkin() {
        return "admin/checkin";
    }
}