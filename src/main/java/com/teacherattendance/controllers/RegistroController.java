package com.teacherattendance.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.service.UserService;

@RestController
@RequestMapping("/registro")
public class RegistroController {
	
	private UserService userService;
	
	@ModelAttribute("usuario")
	public UserDTO returnUserRegistroDto() {
		return new UserDTO();
	}
	
//	@GetMapping
//	public String mostrarFormRegistro() {
//		return "registro";
//	}
	
//	@PostMapping
//	public String registrarCuentaUser(@ModelAttribute("usuario") UserDTO userDto) {
//		userService.createUser(userDto);
//		return "redirect:/registro?exito";
//	}
	
	@PostMapping
	public Usuarios registrarCuentaUser(@RequestBody UserDTO userDto) {
		return userService.createUser(userDto);
	}

}
