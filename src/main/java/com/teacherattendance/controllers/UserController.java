package com.teacherattendance.controllers;

import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/si2")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
    private UserService service;
	
	@Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllAdmins() {
        return ResponseEntity.ok(service.listUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        Usuarios user = service.obtenerUserPorId(id);
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDTO userDto) {
        Usuarios patchAdmin = service.updateAdmin(id, userDto);
        UserDTO patchAdminDto = modelMapper.map(patchAdmin, UserDTO.class);
        return new ResponseEntity<>(patchAdminDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
