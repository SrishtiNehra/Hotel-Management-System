package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.AdminDTO;
import com.hotel.Hotel_Reservation_Management.entity.Admin;

public class AdminMapper {

    public static Admin toEntity(AdminDTO dto) {
        Admin a = new Admin();

        a.setAdminId(dto.getAdminId());
        a.setUsername(dto.getUsername());
        a.setPassword(dto.getPassword());
        a.setFullName(dto.getFullName());
        a.setEmail(dto.getEmail());
        a.setRole(dto.getRole());
        a.setStatus(dto.getStatus());

        return a;
    }

    public static AdminDTO toDTO(Admin a) {
        AdminDTO dto = new AdminDTO();

        dto.setAdminId(a.getAdminId());
        dto.setUsername(a.getUsername());
        dto.setPassword(a.getPassword());
        dto.setFullName(a.getFullName());
        dto.setEmail(a.getEmail());
        dto.setRole(a.getRole());
        dto.setStatus(a.getStatus());

        return dto;
    }
}