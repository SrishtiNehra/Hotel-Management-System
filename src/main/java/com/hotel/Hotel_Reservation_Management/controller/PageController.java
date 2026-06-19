package com.hotel.Hotel_Reservation_Management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    // ---------------- HOME ----------------
    @GetMapping
    public String home() {
        return "index";
    }

    // ---------------- AUTH PAGES ----------------

    @GetMapping("/auth/login")
    public String loginPage() {
        return "auth/login";
    }

    // Role selection page (optional)
    @GetMapping("/auth/register")
    public String registerSelectionPage() {
        return "auth/register";
    }

    // ---------------- REGISTER PAGES ----------------

    @GetMapping("/auth/register/admin")
    public String adminRegisterPage() {
        return "auth/register-admin";
    }

    @GetMapping("/auth/register/customer")
    public String customerRegisterPage() {
        return "auth/register-customer";
    }

    // ---------------- DASHBOARD ----------------

    @GetMapping("/dashboard/admin")
    public String adminDashboard() {
        return "dashboard/admin-dashboard";
    }

    @GetMapping("/dashboard/customer")
    public String customerDashboard() {
        return "dashboard/customer-dashboard";
    }
    
    
}