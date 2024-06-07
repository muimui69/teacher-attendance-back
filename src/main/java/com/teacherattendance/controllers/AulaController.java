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
import com.teacherattendance.dto.AulaDTO;
import com.teacherattendance.entity.Aula;
import com.teacherattendance.service.AulaServiceImp;

@RestController
@RequestMapping("/Aula")
@CrossOrigin(origins = "http://localhost:4200")
public class AulaController {
	
	@Autowired
	private AulaServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<AulaDTO>> listarAula() {
		List<Aula> aula = service.findAll();
		List<AulaDTO> aulaDTO = aula.stream()
				.map(aulas -> modelMapper.map(aula, AulaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(aulaDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public Aula guardarAula(@Validated @RequestBody AulaDTO aulaDto) throws Exception{
		return service.guardarAula(aulaDto); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<AulaDTO> obtenerAula(@PathVariable Long id) {
		Aula aula =  service.obtenerAulaPorId(id);
		AulaDTO aulaDTO = modelMapper.map(aula, AulaDTO.class);
		return ResponseEntity.ok(aulaDTO);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<AulaDTO> actualizarAula(@PathVariable Long id,@RequestBody AulaDTO aulaDTO) {
		Aula aula =  service.actualizarAula(id, aulaDTO);
		AulaDTO aulaActualizado = modelMapper.map(aula, AulaDTO.class);
		return ResponseEntity.ok(aulaActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarAula(@PathVariable Long id) {
		service.eliminarAula(id);
		return ResponseEntity.noContent().build();
	}

}
