package com.teacherattendance.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.util.HttpStatusMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.teacherattendance.dto.AulaDTO;
import com.teacherattendance.entity.Aula;
import com.teacherattendance.service.AulaServiceImp;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/aula")
public class AulaController {
	
	@Autowired
	private AulaServiceImp service;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Aula>>>listarAula() {
		List<Aula> aulas = service.findAll();
		return new ResponseEntity<>(
				ApiResponse.<List<Aula>>builder()
						.statusCode(HttpStatus.OK.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.OK))
						.data(aulas)
						.build(),
				HttpStatus.OK
		);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse<Aula>> guardarAula (@Valid @RequestBody AulaDTO aulaDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Aula>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		try {
			Aula aulaCreada = service.guardarAula(aulaDto);
			return new ResponseEntity<>(
					ApiResponse.<Aula>builder()
							.statusCode(HttpStatus.CREATED.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
							.data(aulaCreada)
							.build(),
					HttpStatus.CREATED
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Aula>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@GetMapping("/{id}")
	public  ResponseEntity<ApiResponse<Aula>> obtenerAula(@PathVariable Long id) {
		try {
	   		Optional<Aula> aulaOpt = service.obtenerAula(id);
			return new ResponseEntity<>(
					ApiResponse.<Aula>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(aulaOpt.get())
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Aula>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<ApiResponse<Aula>>actualizarAula(@PathVariable Long id, @Valid @RequestBody AulaDTO aulaDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Aula>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}

		try {
			Aula aulaActualizada = service.actualizarAula(id, aulaDto);
			return new ResponseEntity<>(
					ApiResponse.<Aula>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(aulaActualizada)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Aula>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<ApiResponse<Void>> eliminarAula(@PathVariable Long id) {
		try {
			service.eliminarAula(id);
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
