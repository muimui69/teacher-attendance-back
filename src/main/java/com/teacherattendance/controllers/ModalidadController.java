package com.teacherattendance.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.teacherattendance.dto.ModalidadDTO;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.util.HttpStatusMessage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import com.teacherattendance.entity.Modalidad;
import com.teacherattendance.service.ModalidadServiceImp;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/modalidad")
public class ModalidadController {
	
	@Autowired
	private ModalidadServiceImp service;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Modalidad>>> listarModalidad() {
		List<Modalidad> modalidades = service.findAll();
		return new ResponseEntity<>(
				ApiResponse.<List<Modalidad>>builder()
						.statusCode(HttpStatus.OK.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.OK))
						.data(modalidades)
						.build(),
				HttpStatus.OK
		);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Modalidad>> obtenerModalidad(@PathVariable Long id) {
		try {
			Optional<Modalidad> modalidadOpt = service.obtenerModalidad(id);
			return new ResponseEntity<>(
					ApiResponse.<Modalidad>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(modalidadOpt.get())
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
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	}

}
