package com.teacherattendance.service;

import com.teacherattendance.dto.AdminDTO;
import com.teacherattendance.entity.AdminEntity;
import com.teacherattendance.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminServiceImp(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdminEntity createAdmin(AdminDTO adminDto) {
        AdminEntity admin = modelMapper.map(adminDto, AdminEntity.class);
        return adminRepository.save(admin);
    }

    @Override
    public AdminEntity patchAdmin(Long id, AdminDTO adminDto) {
        AdminEntity existingAdmin = getAdminById(id);
        modelMapper.map(adminDto, existingAdmin);
        return adminRepository.save(existingAdmin);
    }

    @Override
    public AdminEntity getAdminById(Long id){
        Optional<AdminEntity> adminOptional = adminRepository.findById(id);
        return adminOptional.orElse(null);
    }
}
