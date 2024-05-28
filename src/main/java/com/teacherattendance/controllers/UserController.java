package com.teacherattendance.controllers;

import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.service.UserService;
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
public class UserController {

    private final UserService adminService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService adminService, ModelMapper modelMapper) {
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

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getAdminById(@PathVariable("id") Long id) {
//        Usuarios admin = adminService.getUserById(id);
//        UserDTO adminDto = modelMapper.map(admin, UserDTO.class);
//        return new ResponseEntity<>(adminDto, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserDTO> createAdmin(@Valid @RequestBody UserDTO adminDto) {
//        Usuarios createdAdmin = adminService.createAdmin(adminDto);
//        UserDTO createdAdminDto = modelMapper.map(createdAdmin, UserDTO.class);
//        return new ResponseEntity<>(createdAdminDto, HttpStatus.CREATED);
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateAdmin(@PathVariable("id") Long id, @Valid @RequestBody UserDTO adminDto) {
        Usuarios patchAdmin = adminService.patchAdmin(id, adminDto);
        UserDTO patchAdminDto = modelMapper.map(patchAdmin, UserDTO.class);
        return new ResponseEntity<>(patchAdminDto, HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Long id) {
//        adminService.deleteAdmin(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
