package com.teacherattendance.controllers;

import java.util.List;
<<<<<<< Updated upstream
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
=======
import java.util.Optional;
import java.util.stream.Collectors;

import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.util.HttpStatusMessage;
import jakarta.validation.Valid;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes

import com.teacherattendance.dto.MateriaDTO;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.service.MateriaServiceImp;

@RestController
<<<<<<< Updated upstream
@RequestMapping("/api/materia")
=======
@RequestMapping("/materia")
>>>>>>> Stashed changes
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
				.map(materia -> modelMapper.map(materia, MateriaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(materiaDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public ResponseEntity<Materia> guardarMateria(@RequestBody MateriaDTO materiaDTO) {
		return new ResponseEntity<>(service.guardarMateria(materiaDTO), HttpStatus.OK);
=======
	public ResponseEntity<ApiResponse<Materia>>  guardarMateria(@Valid  @RequestBody MateriaDTO materiaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Materia>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		Materia materiaCreada = service.guardarMateria(materiaDTO);
		return new ResponseEntity<>(
				ApiResponse.<Materia>builder()
						.statusCode(HttpStatus.CREATED.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
						.data(materiaCreada)
						.build(),
				HttpStatus.CREATED
		);
>>>>>>> Stashed changes
	}

	@GetMapping("/{id}")
	public ResponseEntity<MateriaDTO> obtenerMateria(@PathVariable Long id) {
		Materia materia =  service.obtenerMateria(id);
		MateriaDTO materiaDTO = modelMapper.map(materia, MateriaDTO.class);
		return ResponseEntity.ok(materiaDTO);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public ResponseEntity<Materia> actualizarMateria(@PathVariable Long id,@RequestBody MateriaDTO materiaDTO) {
		return new ResponseEntity<>(service.actualizarMateria(id, materiaDTO), HttpStatus.OK);
=======
	public ResponseEntity<ApiResponse<Materia>> actualizarMateria(@PathVariable Long id, @Valid @RequestBody MateriaDTO materiaDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Materia>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}

		try {
			Materia materiaActualizada = service.actualizarMateria(id, materiaDTO);
			return new ResponseEntity<>(
					ApiResponse.<Materia>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(materiaActualizada)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Materia>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
>>>>>>> Stashed changes
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public ResponseEntity<Void>  eliminarMateria(@PathVariable Long id) {
		service.eliminarMateria(id);
		return ResponseEntity.noContent().build();
=======
	public ResponseEntity<ApiResponse<Void>> eliminarMateria(@PathVariable Long id) {
		try {
			service.eliminarMateria(id);
			return new ResponseEntity<>(
					ApiResponse.<Void>builder()
							.statusCode(HttpStatus.NO_CONTENT.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.NO_CONTENT))
							.build(),
					HttpStatus.NO_CONTENT
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Void>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
>>>>>>> Stashed changes
	}
	
}
