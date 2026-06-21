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

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	Authentication authentication ;
	
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
    public ResponseEntity<?> login(@RequestBody LoginDTO dto,
                                   HttpSession session) {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );
        } catch (AuthenticationException e) {

            Map<String, Object> error = new HashMap<>();
            error.put("message", "Bad credentials");
            error.put("status", false);

            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails user = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(user);

        // 🔥 GET CUSTOMER FROM DB
        Long customerId = customerService
                .findByUsername(user.getUsername())
                .map(CustomerDTO::getCustomerId)
                .orElse(null);

        // session (thymeleaf support)
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role",
                user.getAuthorities().iterator().next().getAuthority());

        // RESPONSE DTO
        ResponseDTO response = new ResponseDTO();
        response.setUsername(user.getUsername());
        response.setToken(token);
        response.setRole(user.getAuthorities().iterator().next().getAuthority());

        // 🔥 IMPORTANT FIX
        response.setCustomerId(customerId);

        return ResponseEntity.ok(response);
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
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/auth/login";
    }
}