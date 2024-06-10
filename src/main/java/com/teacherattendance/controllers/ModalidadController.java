package com.teacherattendance.controllers;

import java.util.List;
<<<<<<< Updated upstream
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
=======
import java.util.Optional;
import java.util.stream.Collectors;

import com.teacherattendance.dto.ModalidadDTO;
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

import com.teacherattendance.dto.ModalidadDTO;
=======
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes
import com.teacherattendance.entity.Modalidad;
import com.teacherattendance.service.ModalidadServiceImp;

@RestController
<<<<<<< Updated upstream
@RequestMapping("/api/modalidad")
=======
@RequestMapping("/modalidad")
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	public ResponseEntity<Modalidad> guardarModalidad(@RequestBody ModalidadDTO modalidadDTO) {
		return new ResponseEntity<>(service.guardarModalidad(modalidadDTO), HttpStatus.OK);
=======
	public ResponseEntity<ApiResponse<Modalidad>> guardarModalidad(@Valid @RequestBody ModalidadDTO modalidadDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Modalidad>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		Modalidad modalidadCreada = service.guardarModalidad(modalidadDTO);
		return new ResponseEntity<>(
				ApiResponse.<Modalidad>builder()
						.statusCode(HttpStatus.CREATED.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
						.data(modalidadCreada)
						.build(),
				HttpStatus.CREATED
		);
>>>>>>> Stashed changes
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModalidadDTO> obtenerModalidad(@PathVariable Long id) {
		Modalidad modalidad =  service.obtenerModalidad(id);
		ModalidadDTO modalidadDTO = modelMapper.map(modalidad, ModalidadDTO.class);
		return ResponseEntity.ok(modalidadDTO);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public ResponseEntity<Modalidad> actualizarModalidad(@PathVariable Long id,@RequestBody ModalidadDTO modalidadDTO) {
		return new ResponseEntity<>(service.actualizarModalidad(id, modalidadDTO), HttpStatus.OK); 
=======
	public ResponseEntity<ApiResponse<Modalidad>> actualizarModalidad(@PathVariable Long id,@Valid @RequestBody ModalidadDTO modalidadDTO,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Modalidad>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}

		try {
			Modalidad modalidadActualizada = service.actualizarModalidad(id, modalidadDTO);
			return new ResponseEntity<>(
					ApiResponse.<Modalidad>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(modalidadActualizada)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Modalidad>builder()
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
	public ResponseEntity<Void> eliminarModalidad(@PathVariable Long id) {
		service.eliminarModalidad(id);
		return ResponseEntity.noContent().build();
=======
	public  ResponseEntity<ApiResponse<Void>> eliminarModalidad(@PathVariable Long id) {
		try {
			service.eliminarModalidad(id);
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
