package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.LoginDTO;
import com.hotel.Hotel_Reservation_Management.dto.ResponseDTO;
import com.hotel.Hotel_Reservation_Management.dto.AdminDTO;
import com.hotel.Hotel_Reservation_Management.dto.CustomerDTO;
import com.hotel.Hotel_Reservation_Management.security.JwtUtil;
import com.hotel.Hotel_Reservation_Management.service.AdminService;
import com.hotel.Hotel_Reservation_Management.service.CustomerService;
import com.hotel.Hotel_Reservation_Management.validator.AdminValidator;
import com.hotel.Hotel_Reservation_Management.validator.CustomerValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminValidator adminValidator;

    @Autowired
    private CustomerValidator customerValidator;

    // -------------------------
    // LOGIN
    // -------------------------
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginDTO dto) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getUsername(),
                                dto.getPassword()
                        )
                );

        UserDetails user = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(user);

        ResponseDTO response = new ResponseDTO();
        response.setToken(token);
        response.setRole(user.getAuthorities().iterator().next().getAuthority());

        return response;
    }

    // -------------------------
    // REGISTER ADMIN
    // -------------------------
    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody AdminDTO dto) {

        adminValidator.validate(dto);
        adminService.createAdmin(dto);

        return "Admin registered successfully";
    }

    // -------------------------
    // REGISTER CUSTOMER
    // -------------------------
    @PostMapping("/register/customer")
    public String registerCustomer(@RequestBody CustomerDTO dto) {

        customerValidator.validate(dto);
        customerService.createCustomer(dto);

        return "Customer registered successfully";
    }
}