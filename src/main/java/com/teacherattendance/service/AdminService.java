package com.teacherattendance.service;

import com.teacherattendance.dto.AdminDTO;
import com.teacherattendance.entity.AdminEntity;

import java.util.List;

public interface AdminService {

//    List<AdminEntity> getAllAdmins();

    AdminEntity getAdminById(Long id);

    AdminEntity createAdmin(AdminDTO adminDto);

    AdminEntity patchAdmin(Long id, AdminDTO adminDto);

//    void deleteAdmin(Long id);
}
