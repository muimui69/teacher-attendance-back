package com.teacherattendance.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.teacherattendance.entity.Carrera;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.util.HttpStatusMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.service.CarreraServiceImp;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
	
	@Autowired
	private CarreraServiceImp service;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Carrera>>> listarCarreras() {
		List<Carrera> carreras = service.findAll();
		return new ResponseEntity<>(
				ApiResponse.<List<Carrera>>builder()
						.statusCode(HttpStatus.OK.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.OK))
						.data(carreras)
						.build(),
				HttpStatus.OK
		);
	}


	@PostMapping
	public ResponseEntity<ApiResponse<Carrera>> guardarCarrera(@Valid @RequestBody CarreraDTO carreraDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Carrera>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		Carrera carreraCreada = service.guardarCarrera(carreraDTO);
		return new ResponseEntity<>(
				ApiResponse.<Carrera>builder()
						.statusCode(HttpStatus.CREATED.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
						.data(carreraCreada)
						.build(),
				HttpStatus.CREATED
		);
	}


	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Carrera>> obtenerCarrera(@PathVariable Long id) {
		try {
			Optional<Carrera> carreraOpt = service.obtenerCarrera(id);
			return new ResponseEntity<>(
					ApiResponse.<Carrera>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(carreraOpt.get())
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Carrera>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}



	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Carrera>> actualizarCarrera(@PathVariable Long id, @Valid @RequestBody CarreraDTO carreraDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Carrera>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}

		try {
			Carrera carreraActualizada = service.actualizarCarrera(id, carreraDTO);
			return new ResponseEntity<>(
					ApiResponse.<Carrera>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(carreraActualizada)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Carrera>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminarCarrera(@PathVariable Long id) {
		try {
			service.eliminarCarrera(id);
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
