package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.AdminDto;
import com.hotel.Hotel_Reservation_Management.entity.Admin;

public class AdminMapper {

    public static AdminDto toDto(Admin admin) {
        if (admin == null) return null;

        AdminDto dto = new AdminDto();
        dto.setAdminId(admin.getAdminId());
        dto.setFullName(admin.getFullName());
        dto.setEmail(admin.getEmail());
        dto.setUsername(admin.getUsername());
        dto.setPassword(admin.getPassword());

        return dto;
    }

    public static Admin toEntity(AdminDto dto) {
        if (dto == null) return null;

        Admin admin = new Admin();
        admin.setAdminId(dto.getAdminId());
        admin.setFullName(dto.getFullName());
        admin.setEmail(dto.getEmail());
        admin.setUsername(dto.getUsername());
        admin.setPassword(dto.getPassword());

        return admin;
    }
}