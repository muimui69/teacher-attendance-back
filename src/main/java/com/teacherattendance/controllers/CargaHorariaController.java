package com.teacherattendance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Optional;
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
import com.teacherattendance.dto.CargaHorariaDTO;
import com.teacherattendance.entity.CargaHoraria;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.CargaHorariaServiceImp;
import com.teacherattendance.util.HttpStatusMessage;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carga-horaria")
@CrossOrigin(origins = "*")
public class CargaHorariaController {
	
	@Autowired
	private CargaHorariaServiceImp service;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<CargaHoraria>>> listarCargaHoraria() {
		List<CargaHoraria> cargaHorarias = service.findAll();
		return new ResponseEntity<>(
				ApiResponse.<List<CargaHoraria>>builder()
						.statusCode(HttpStatus.OK.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.OK))
						.data(cargaHorarias)
						.build(),
				HttpStatus.OK
		);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<ApiResponse<CargaHoraria>> guardarCargaHoraria(@Valid @RequestBody CargaHorariaDTO cargaHorariaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<CargaHoraria>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		CargaHoraria cargaHorariaCreado = service.guardarCargaHoraria(cargaHorariaDTO);
		return new ResponseEntity<>(
				ApiResponse.<CargaHoraria>builder()
						.statusCode(HttpStatus.CREATED.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
						.data(cargaHorariaCreado)
						.build(),
				HttpStatus.CREATED
		);
	}


	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CargaHoraria>> obtenerCargaHoraria(@PathVariable Long id) {
		try {
			Optional<CargaHoraria> cargaHorariaOpt = service.obtenerCargaHoraria(id);
			return new ResponseEntity<>(
					ApiResponse.<CargaHoraria>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(cargaHorariaOpt.get())
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<CargaHoraria>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse<CargaHoraria>> actualizarCargaHoraria(@PathVariable Long id, @Valid @RequestBody CargaHorariaDTO cargaHorariaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<CargaHoraria>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}

		try {
			CargaHoraria cargaHorariaActualizada = service.actualizarCargaHoraria(id, cargaHorariaDTO);
			return new ResponseEntity<>(
					ApiResponse.<CargaHoraria>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(cargaHorariaActualizada)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<CargaHoraria>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse<Void>> eliminarCargaHoraria(@PathVariable Long id) {
		try {
			service.eliminarCargaHoraria(id);
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
