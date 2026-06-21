package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.AdminDTO;
import java.util.List;
public interface AdminService {

    AdminDTO createAdmin(AdminDTO dto);

    AdminDTO getAdminById(Long id);

    List<AdminDTO> getAllAdmins();

    AdminDTO updateAdmin(Long id, AdminDTO dto);

    void deleteAdmin(Long id);

    AdminDTO getByUsername(String username);
}