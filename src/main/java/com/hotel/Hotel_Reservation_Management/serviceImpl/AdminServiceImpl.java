package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.AdminDto;
import com.hotel.Hotel_Reservation_Management.entity.Admin;
import com.hotel.Hotel_Reservation_Management.mapper.AdminMapper;
import com.hotel.Hotel_Reservation_Management.repository.AdminRepository;
import com.hotel.Hotel_Reservation_Management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AdminDto createAdmin(AdminDto dto) {

        Admin admin = AdminMapper.toEntity(dto);

        admin.setPassword(passwordEncoder.encode(dto.getPassword()));

        return AdminMapper.toDto(adminRepository.save(admin));
    }

    @Override
    public AdminDto getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return AdminMapper.toDto(admin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(AdminMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDto updateAdmin(Long id, AdminDto dto) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setFullName(dto.getFullName());
        admin.setEmail(dto.getEmail());
        admin.setUsername(dto.getUsername());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return AdminMapper.toDto(adminRepository.save(admin));
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}