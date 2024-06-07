package com.teacherattendance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.dto.MateriaDTO;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.service.MateriaServiceImp;

@RestController
@RequestMapping("/Materia")
@CrossOrigin(origins = "http://localhost:4200")
public class MateriaController {

	@Autowired
	private MateriaServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<MateriaDTO>> listarMateria() {
		List<Materia> materias = service.findAll();
		List<MateriaDTO> materiaDTOs = materias.stream()
				.map(maateria -> modelMapper.map(materias, MateriaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(materiaDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Materia> guardarMateria(@RequestBody MateriaDTO materiaDTO) {
		return new ResponseEntity<>(service.guardarMateria(materiaDTO), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MateriaDTO> obtenerMateria(@PathVariable Long id) {
		Materia materia =  service.obtenerMateria(id);
		MateriaDTO materiaDTO = modelMapper.map(materia, MateriaDTO.class);
		return ResponseEntity.ok(materiaDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Materia> actualizarMateria(@PathVariable Long id,@RequestBody MateriaDTO materiaDTO) {
		return new ResponseEntity<>(service.actualizarMateria(id, materiaDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  eliminarMateria(@PathVariable Long id) {
		service.eliminarMateria(id);
		return ResponseEntity.noContent().build();
	}
	
}
