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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teacherattendance.dto.ModuloDTO;
import com.teacherattendance.entity.Modulo;
import com.teacherattendance.service.ModuloServiceImp;

@RestController
@RequestMapping("/Modulo")
@CrossOrigin(origins = "http://localhost:4200")
public class ModuloController {
	
	@Autowired
	private ModuloServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<ModuloDTO>> listarModulos(){
		List<Modulo> modulos = service.findAll();
		List<ModuloDTO> moduloDTOs = modulos.stream()
				.map(modulo -> modelMapper.map(modulos, ModuloDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(moduloDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Modulo> guardarModulo(@RequestBody ModuloDTO moduloDTO) {
		return new ResponseEntity<>(service.guardarModulo(moduloDTO), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModuloDTO> obtenerModulo(@PathVariable Long id) {
		Modulo modulo =  service.obtenerModulo(id);
		ModuloDTO moduloDTO = modelMapper.map(modulo, ModuloDTO.class);
		return ResponseEntity.ok(moduloDTO);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Modulo> actualizarModulo(@PathVariable Long id,@RequestBody ModuloDTO moduloDTO) {
		return new ResponseEntity<>(service.actualizarModulo(id, moduloDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  eliminarModulo(@PathVariable Long id) {
		service.eliminarModulo(id);
		return ResponseEntity.noContent().build();
	}


}
