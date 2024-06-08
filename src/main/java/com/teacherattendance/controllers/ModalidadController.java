package com.teacherattendance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.dto.ModalidadDTO;
import com.teacherattendance.entity.Modalidad;
import com.teacherattendance.service.ModalidadServiceImp;

@RestController
@RequestMapping("/api/modalidad")
@CrossOrigin(origins = "http://localhost:4200")
public class ModalidadController {
	
	@Autowired
	private ModalidadServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<ModalidadDTO>> listarModalidad() {
		List<Modalidad> modalidads = service.findAll();
		List<ModalidadDTO> modalidadDTOs = modalidads.stream()
				.map(modalidad -> modelMapper.map(modalidad, ModalidadDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(modalidadDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Modalidad> guardarModalidad(@RequestBody ModalidadDTO modalidadDTO) {
		return new ResponseEntity<>(service.guardarModalidad(modalidadDTO), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModalidadDTO> obtenerModalidad(@PathVariable Long id) {
		Modalidad modalidad =  service.obtenerModalidad(id);
		ModalidadDTO modalidadDTO = modelMapper.map(modalidad, ModalidadDTO.class);
		return ResponseEntity.ok(modalidadDTO);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Modalidad> actualizarModalidad(@PathVariable Long id,@RequestBody ModalidadDTO modalidadDTO) {
		return new ResponseEntity<>(service.actualizarModalidad(id, modalidadDTO), HttpStatus.OK); 
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> eliminarModalidad(@PathVariable Long id) {
		service.eliminarModalidad(id);
		return ResponseEntity.noContent().build();
	}

}
