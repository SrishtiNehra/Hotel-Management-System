package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.AdminDto;
import java.util.List;

public interface AdminService {

    AdminDto createAdmin(AdminDto adminDto);

    AdminDto getAdminById(Long id);

    List<AdminDto> getAllAdmins();

    AdminDto updateAdmin(Long id, AdminDto adminDto);

    void deleteAdmin(Long id);
}