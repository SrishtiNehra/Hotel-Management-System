package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.AdminDto;
import com.hotel.Hotel_Reservation_Management.dto.CustomerDto;
import com.hotel.Hotel_Reservation_Management.service.AdminService;
import com.hotel.Hotel_Reservation_Management.service.CustomerService;
import com.hotel.Hotel_Reservation_Management.validator.AdminValidator;
import com.hotel.Hotel_Reservation_Management.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminValidator adminValidator;

    @Autowired
    private CustomerValidator customerValidator;

    // -----------------------------
    // REGISTER ADMIN
    // -----------------------------
    @PostMapping("/register/admin")
    public AdminDto registerAdmin(@RequestBody AdminDto dto) {

        adminValidator.validate(dto);
        return adminService.createAdmin(dto);
    }

    // -----------------------------
    // REGISTER CUSTOMER
    // -----------------------------
    @PostMapping("/register/customer")
    public CustomerDto registerCustomer(@RequestBody CustomerDto dto) {

        customerValidator.validate(dto);
        return customerService.createCustomer(dto);
    }

    // -----------------------------
    // LOGIN INFO (handled by Spring Security)
    // -----------------------------
    @PostMapping("/login")
    public String loginInfo() {
        return "Login is handled by Spring Security. Use /login endpoint with username & password.";
    }

    // -----------------------------
    // LOGOUT INFO
    // -----------------------------
    @GetMapping("/logout")
    public String logoutInfo() {
        return "Logout handled by Spring Security automatically.";
    }
}