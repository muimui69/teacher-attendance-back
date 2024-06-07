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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teacherattendance.dto.CargaHorariaDTO;
import com.teacherattendance.entity.CargaHoraria;
import com.teacherattendance.service.CargaHorariaServiceImp;

@RestController
@RequestMapping("/CargaHoraria")
@CrossOrigin(origins = "http://localhost:4200")
public class CargaHorariaController {
	
	@Autowired
	private CargaHorariaServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<CargaHorariaDTO>> listarCargaHoraria(){
		List<CargaHoraria> cargaHoraria = service.findAll();
		List<CargaHorariaDTO> cargaHorariaDTO = cargaHoraria.stream()
				.map(carga -> modelMapper.map(cargaHoraria, CargaHorariaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(cargaHorariaDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CargaHoraria> guardarCargaHoraria(@Validated @RequestBody CargaHorariaDTO cargaHorariaDTO) throws Exception{
		return new ResponseEntity<>(service.guardarCargaHoraria(cargaHorariaDTO), HttpStatus.OK); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<CargaHorariaDTO> obtenerCargaHoraria(@PathVariable Long id) {
		CargaHoraria cargaHoraria =  service.obtenerCargaHorariaPorId(id);
		CargaHorariaDTO cargaHorariaDTO = modelMapper.map(cargaHoraria, CargaHorariaDTO.class);
		return ResponseEntity.ok(cargaHorariaDTO);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CargaHoraria> actualizarCargaHoraria(@PathVariable Long id,@RequestBody CargaHorariaDTO cargaHorariaDTO) {
		return new ResponseEntity<>(service.actualizarCargaHoraria(id, cargaHorariaDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCargaHoraria(@PathVariable Long id) {
		service.eliminarCargaHoraria(id);
		return ResponseEntity.noContent().build();
	}

}
