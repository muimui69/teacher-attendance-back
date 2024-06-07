package com.teacherattendance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.dto.LicenciaDTO;
import com.teacherattendance.entity.Licencia;
import com.teacherattendance.service.LicenciaServiceImp;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Licencia")
public class LicenciaController {
	
	@Autowired
	private LicenciaServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<LicenciaDTO>> listarLicencia() {
		List<Licencia> licencias = service.findAll();
		List<LicenciaDTO> licenciaDTOs = licencias.stream()
				.map(licencia -> modelMapper.map(licencias, LicenciaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(licenciaDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Licencia> guardarLicencia(@Validated @RequestBody LicenciaDTO licenciaDto) throws Exception{
		return new ResponseEntity<>(service.guardarLicencia(licenciaDto), HttpStatus.OK); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<LicenciaDTO> obtenerLicencia(@PathVariable Long id) {
		Licencia licencia =  service.obtenerLicencia(id);
		LicenciaDTO licenciaDTO = modelMapper.map(licencia, LicenciaDTO.class);
		return ResponseEntity.ok(licenciaDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Licencia> actualizarLicencia(@PathVariable Long id,@RequestBody LicenciaDTO licenciaDTO) {
		return new ResponseEntity<>(service.actualizarLicencia(id, licenciaDTO), HttpStatus.OK); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarLicencia(@PathVariable Long id) {
		service.eliminarLicencia(id);
		return ResponseEntity.noContent().build();
	}

}
