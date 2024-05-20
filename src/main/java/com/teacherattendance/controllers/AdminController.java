package com.teacherattendance.controllers;

import com.teacherattendance.dto.AdminDTO;
import com.teacherattendance.entity.AdminEntity;
import com.teacherattendance.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminController(AdminService adminService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

//    @GetMapping
//    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
//        List<AdminEntity> admins = adminService.ge();
//        List<AdminDTO> adminDtos = admins.stream()
//                .map(admin -> modelMapper.map(admin, AdminDTO.class))
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(adminDtos, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable("id") Long id) {
        AdminEntity admin = adminService.getAdminById(id);
        AdminDTO adminDto = modelMapper.map(admin, AdminDTO.class);
        return new ResponseEntity<>(adminDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDto) {
        AdminEntity createdAdmin = adminService.createAdmin(adminDto);
        AdminDTO createdAdminDto = modelMapper.map(createdAdmin, AdminDTO.class);
        return new ResponseEntity<>(createdAdminDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable("id") Long id, @Valid @RequestBody AdminDTO adminDto) {
        AdminEntity patchAdmin = adminService.patchAdmin(id, adminDto);
        AdminDTO patchAdminDto = modelMapper.map(patchAdmin, AdminDTO.class);
        return new ResponseEntity<>(patchAdminDto, HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Long id) {
//        adminService.deleteAdmin(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
