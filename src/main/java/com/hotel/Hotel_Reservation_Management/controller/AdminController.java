package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.AdminDto;
import com.hotel.Hotel_Reservation_Management.service.AdminService;
import com.hotel.Hotel_Reservation_Management.validator.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminValidator adminValidator;

    @PostMapping
    public AdminDto create(@RequestBody AdminDto dto) {
        adminValidator.validate(dto);
        return adminService.createAdmin(dto);
    }

    @GetMapping("/{id}")
    public AdminDto getById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @GetMapping
    public List<AdminDto> getAll() {
        return adminService.getAllAdmins();
    }

    @PutMapping("/{id}")
    public AdminDto update(@PathVariable Long id, @RequestBody AdminDto dto) {
        adminValidator.validate(dto);
        return adminService.updateAdmin(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}