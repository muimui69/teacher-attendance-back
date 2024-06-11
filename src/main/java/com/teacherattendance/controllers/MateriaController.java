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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import com.teacherattendance.dto.MateriaDTO;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.service.MateriaServiceImp;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/materia")
// @CrossOrigin(origins = "http://localhost:4200")
public class MateriaController {

	@Autowired
	private MateriaServiceImp service;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Materia>>> listarMaterias() {
		List<Materia> materias = service.findAll();
		return new ResponseEntity<>(
				ApiResponse.<List<Materia>>builder()
						.statusCode(HttpStatus.OK.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.OK))
						.data(materias)
						.build(),
				HttpStatus.OK
		);
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Materia>>  obtenerMateria(@PathVariable Long id) {
		try {
			Optional<Materia> materiaOpt = service.obtenerMateria(id);
			return new ResponseEntity<>(
					ApiResponse.<Materia>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(materiaOpt.get())
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
	}


	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	}
	
}
