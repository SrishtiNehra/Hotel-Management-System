package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.AdminDTO;
import com.hotel.Hotel_Reservation_Management.service.AdminService;
import com.hotel.Hotel_Reservation_Management.validator.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminValidator adminValidator;
    
    @GetMapping("/profile")
    public AdminDTO getProfile(Authentication auth) {
        return adminService.getByUsername(auth.getName());
    }

    @PostMapping
    public AdminDTO create(@RequestBody AdminDTO dto) {
        adminValidator.validate(dto);
        return adminService.createAdmin(dto);
    }

    @GetMapping("/{id}")
    public AdminDTO getById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @GetMapping
    public List<AdminDTO> getAll() {
        return adminService.getAllAdmins();
    }

    @PutMapping("/{id}")
    public AdminDTO update(@PathVariable Long id, @RequestBody AdminDTO dto) {
        adminValidator.validate(dto);
        return adminService.updateAdmin(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}