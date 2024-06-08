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
import com.teacherattendance.dto.PeriodoDTO;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.service.PeriodoServiceImp;

@RestController
@RequestMapping("/api/periodo")
@CrossOrigin(origins = "http://localhost:4200")
public class PeriodoController {
	
	@Autowired
	private PeriodoServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<PeriodoDTO>> listarPeriodos(){
		List<Periodo> periodos = service.findAll();
		List<PeriodoDTO> periodoDTOs = periodos.stream()
				.map(periodo -> modelMapper.map(periodo, PeriodoDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(periodoDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Periodo> guardarPeriodo(@Validated @RequestBody PeriodoDTO periodoDTO) throws Exception{
		return new ResponseEntity<>(service.guardarPeriodo(periodoDTO), HttpStatus.OK); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<PeriodoDTO> obtenerPeriodo(@PathVariable Long id) {
		Periodo periodo =  service.obtenerPeriodo(id);
		PeriodoDTO periodoDTO = modelMapper.map(periodo, PeriodoDTO.class);
		return ResponseEntity.ok(periodoDTO);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Periodo> actualizarPeriodo(@PathVariable Long id,@RequestBody PeriodoDTO periodoDTO) {
		return new ResponseEntity<>(service.actualizarPeriodo(id, periodoDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> eliminarCargaHoraria(@PathVariable Long id) {
		service.eliminarPeriodo(id);
		return ResponseEntity.noContent().build();
	}

}
