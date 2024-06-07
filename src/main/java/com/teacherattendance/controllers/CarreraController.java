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
import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.service.CarreraServiceImp;

@RestController
@RequestMapping("/Carrera")
@CrossOrigin(origins = "http://localhost:4200")
public class CarreraController {
	
	@Autowired
	private CarreraServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<CarreraDTO>> listarCarrera() {
		List<Carrera> carrera = service.findAll();
		List<CarreraDTO> carreraDTO = carrera.stream()
				.map(carreras -> modelMapper.map(carrera, CarreraDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(carreraDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public Carrera guardarCarrera(@RequestBody CarreraDTO carreraDTO) {
		return service.guardarCarrera(carreraDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarreraDTO> obtenerCarrera(@PathVariable Long id) {
		Carrera carrera =  service.obtenerCarrera(id);
		CarreraDTO carreraDTO = modelMapper.map(carrera, CarreraDTO.class);
		return new ResponseEntity<>(carreraDTO, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CarreraDTO> actualizarCarrera(@PathVariable Long id,@RequestBody CarreraDTO detalleCarrera) {
		Carrera carreraActualizado = service.actualizarCarrera(id, detalleCarrera);
		CarreraDTO carreraDTO = modelMapper.map(carreraActualizado, CarreraDTO.class);
		return ResponseEntity.ok(carreraDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCarrera(@PathVariable Long id) {
		service.eliminarCarrera(id);
		return ResponseEntity.noContent().build();
	}

}
