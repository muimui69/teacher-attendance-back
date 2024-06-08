package com.teacherattendance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.auth.AuthResponse;
import com.teacherattendance.auth.LoginRequest;
import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.service.UserServiceImp;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RegistroController {
	
	@Autowired
	private UserServiceImp userService;

	@PostMapping(value = "login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(userService.login(request));
	}
	
	@PostMapping(value = "register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserDTO userDto) {
		return ResponseEntity.ok(userService.createUser(userDto));
	}
	
	@PostMapping(value = "registerAdmin")
	public ResponseEntity<AuthResponse> registerAdmin(@RequestBody UserDTO userDto) {
		return ResponseEntity.ok(userService.createUserAdmin(userDto));
	}

}
