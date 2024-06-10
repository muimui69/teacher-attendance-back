package com.teacherattendance.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.teacherattendance.dto.AsistenciaDTO;
import com.teacherattendance.entity.Asistencia;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.AsistenciaServiceImp;
import com.teacherattendance.util.HttpStatusMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asistencia")
@CrossOrigin(origins = "http://localhost:4200")
public class AsistenciaController {
	
	@Autowired
	private AsistenciaServiceImp service;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Asistencia>>> listarMaterias() {
		List<Asistencia> asistencias = service.findAll();
		return new ResponseEntity<>(
				ApiResponse.<List<Asistencia>>builder()
						.statusCode(HttpStatus.OK.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.OK))
						.data(asistencias)
						.build(),
				HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Asistencia>>  guardarAsistencia(@Valid  @RequestBody AsistenciaDTO asistenciaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Asistencia>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		Asistencia asistenciaCreada = service.guardarAsistencia(asistenciaDTO);
		return new ResponseEntity<>(
				ApiResponse.<Asistencia>builder()
						.statusCode(HttpStatus.CREATED.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
						.data(asistenciaCreada)
						.build(),
				HttpStatus.CREATED
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Asistencia>> obtenerAsistencia(@PathVariable Long id) {
		try {
			Optional<Asistencia> asistenciaOpt = service.obtenerAsistencia(id);
			return new ResponseEntity<>(
					ApiResponse.<Asistencia>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(asistenciaOpt.get())
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Asistencia>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Asistencia>> actualizarAsistencia(@PathVariable Long id, 
			@Valid @RequestBody AsistenciaDTO asistenciaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Asistencia>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		try {
			Asistencia asistenciaActualizada = service.actualizarAsistencia(id, asistenciaDTO);
			return new ResponseEntity<>(
					ApiResponse.<Asistencia>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(asistenciaActualizada)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Asistencia>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminarAsistencia(@PathVariable Long id) {
		try {
			service.eliminarAsistencia(id);
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
