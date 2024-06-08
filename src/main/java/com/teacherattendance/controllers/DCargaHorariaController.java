package com.teacherattendance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.dto.DetalleCargaHorariaDTO;
import com.teacherattendance.entity.DetalleCargaHoraria;
import com.teacherattendance.service.DCargaHorariaServiceImp;

@RestController
@RequestMapping("/api/detalleCargaHoraria")
@CrossOrigin(origins = "http://localhost:4200")
public class DCargaHorariaController {
	
	@Autowired
	private DCargaHorariaServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<DetalleCargaHorariaDTO>> listarDetalleCargaHoraria(){
		List<DetalleCargaHoraria> detalleCargaHoraria = service.findAll();
		List<DetalleCargaHorariaDTO> detalleCargaHorariaDTO = detalleCargaHoraria.stream()
				.map(detalleCarga -> modelMapper.map(detalleCarga, DetalleCargaHorariaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(detalleCargaHorariaDTO, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<DetalleCargaHoraria> guardarDCargaHoraria(
			@Validated @RequestBody DetalleCargaHorariaDTO detalleCargaHorariaDTO) throws Exception{
		return new ResponseEntity<>(service.guardarDetalleCargaHoraria(detalleCargaHorariaDTO), HttpStatus.OK); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalleCargaHorariaDTO> obtenerCargaHoraria(@PathVariable Long id) {
		DetalleCargaHoraria detalleCargaHoraria =  service.obtenerDetalleCargaHoraria(id);
		DetalleCargaHorariaDTO detalleCargaHorariaDTO = modelMapper.map(detalleCargaHoraria, DetalleCargaHorariaDTO.class);
		return ResponseEntity.ok(detalleCargaHorariaDTO);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<DetalleCargaHoraria> actualizarDetalleCargaHoraria(@PathVariable Long id,
			@RequestBody DetalleCargaHorariaDTO detalleCargaHorariaDTO) {
		return new ResponseEntity<>(service.actualizarDetalleCargaHoraria(id, detalleCargaHorariaDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> eliminarDetalleCargaHoraria(@PathVariable Long id) {
		service.eliminarDetalleCargaHoraria(id);
		return ResponseEntity.noContent().build();
	}

}
