package com.hotel.Hotel_Reservation_Management.serviceImpl;

import com.hotel.Hotel_Reservation_Management.dto.AdminDTO;
import com.hotel.Hotel_Reservation_Management.entity.Admin;
import com.hotel.Hotel_Reservation_Management.exception.ResourceNotFoundException;
import com.hotel.Hotel_Reservation_Management.mapper.AdminMapper;
import com.hotel.Hotel_Reservation_Management.repository.AdminRepository;
import com.hotel.Hotel_Reservation_Management.service.AdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	@Autowired
    private final AdminRepository adminRepository;
	
	@Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminDTO createAdmin(AdminDTO dto) {

        Admin admin = AdminMapper.toEntity(dto);
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));

        return AdminMapper.toDTO(adminRepository.save(admin));
    }

    @Override
    public AdminDTO getAdminById(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));

        return AdminMapper.toDTO(admin);
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(AdminMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO updateAdmin(Long id, AdminDTO dto) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));

        if (dto.getFullName() != null)
            admin.setFullName(dto.getFullName());

        if (dto.getEmail() != null)
            admin.setEmail(dto.getEmail());

        if (dto.getUsername() != null)
            admin.setUsername(dto.getUsername());

        if (dto.getPassword() != null && !dto.getPassword().isBlank())
            admin.setPassword(passwordEncoder.encode(dto.getPassword()));

        return AdminMapper.toDTO(adminRepository.save(admin));
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    // 🔐 IMPORTANT FOR PROFILE PAGE
    @Override
    public AdminDTO getByUsername(String username) {

        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with username: " + username));

        return AdminMapper.toDTO(admin);
    }
}