package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.AdminDTO;
import com.hotel.Hotel_Reservation_Management.dto.CustomerDTO;
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
    public AdminDTO registerAdmin(@RequestBody AdminDTO dto) {

        adminValidator.validate(dto);
        return adminService.createAdmin(dto);
    }

    // -----------------------------
    // REGISTER CUSTOMER
    // -----------------------------
    @PostMapping("/register/customer")
    public CustomerDTO registerCustomer(@RequestBody CustomerDTO dto) {

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