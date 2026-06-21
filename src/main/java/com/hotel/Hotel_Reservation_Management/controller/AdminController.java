package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.AdminDTO;
import com.hotel.Hotel_Reservation_Management.dto.CustomerDTO;
import com.hotel.Hotel_Reservation_Management.dto.ReservationDTO;
import com.hotel.Hotel_Reservation_Management.service.AdminService;
import com.hotel.Hotel_Reservation_Management.service.CustomerService;
import com.hotel.Hotel_Reservation_Management.service.ReservationService;
import com.hotel.Hotel_Reservation_Management.validator.AdminValidator;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
    private final AdminService adminService;
	
	@Autowired
    private final ReservationService reservationService;
	
	@Autowired
    private final CustomerService customerService;
	
	@Autowired
    private final AdminValidator adminValidator;

    // 🔐 LOGGED-IN ADMIN PROFILE (BEST PRACTICE)
    @GetMapping("/profile")
    public AdminDTO getProfile(Authentication auth) {
        return adminService.getByUsername(auth.getName());
    }

    // CREATE
    @PostMapping
    public AdminDTO create(@RequestBody AdminDTO dto) {
        adminValidator.validate(dto);
        return adminService.createAdmin(dto);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public AdminDTO getById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    // GET ALL
    @GetMapping
    public List<AdminDTO> getAll() {
        return adminService.getAllAdmins();
    }

    // UPDATE
    @PutMapping("/{id}")
    public AdminDTO update(@PathVariable Long id, @RequestBody AdminDTO dto) {
//        adminValidator.validate(dto);
        return adminService.updateAdmin(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
    
    @GetMapping("/reservations")
    public List<ReservationDTO> getReservations() {
        return reservationService.getAllReservations();
    }
    
    @GetMapping("/customers")
    public List<CustomerDTO> getCustomers() {
        return customerService.getAllCustomers();
    }
}