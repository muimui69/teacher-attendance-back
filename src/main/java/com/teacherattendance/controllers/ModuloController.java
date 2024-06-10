package com.teacherattendance.controllers;

import java.util.List;
<<<<<<< Updated upstream
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
=======
import java.util.Optional;
import java.util.stream.Collectors;

import com.teacherattendance.dto.ModuloDTO;
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
import com.teacherattendance.dto.ModuloDTO;
=======
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes
import com.teacherattendance.entity.Modulo;
import com.teacherattendance.service.ModuloServiceImp;

@RestController
<<<<<<< Updated upstream
@RequestMapping("/api/modulo")
=======
@RequestMapping("/modulo")
>>>>>>> Stashed changes
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
				.map(modulo -> modelMapper.map(modulo, ModuloDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(moduloDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public ResponseEntity<Modulo> guardarModulo(@RequestBody ModuloDTO moduloDTO) {
		return new ResponseEntity<>(service.guardarModulo(moduloDTO), HttpStatus.OK);
=======
	public  ResponseEntity<ApiResponse<Modulo>> guardarModulo(@Valid @RequestBody ModuloDTO moduloDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Modulo>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		Modulo moduloCreado = service.guardarModulo(moduloDTO);
		return new ResponseEntity<>(
				ApiResponse.<Modulo>builder()
						.statusCode(HttpStatus.CREATED.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
						.data(moduloCreado)
						.build(),
				HttpStatus.CREATED
		);
>>>>>>> Stashed changes
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModuloDTO> obtenerModulo(@PathVariable Long id) {
		Modulo modulo =  service.obtenerModulo(id);
		ModuloDTO moduloDTO = modelMapper.map(modulo, ModuloDTO.class);
		return ResponseEntity.ok(moduloDTO);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public ResponseEntity<Modulo> actualizarModulo(@PathVariable Long id,@RequestBody ModuloDTO moduloDTO) {
		return new ResponseEntity<>(service.actualizarModulo(id, moduloDTO), HttpStatus.OK);
=======
	public ResponseEntity<ApiResponse<Modulo>> actualizarModulo(@PathVariable Long id, @Valid @RequestBody ModuloDTO moduloDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Modulo>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}

		try {
			Modulo moduloActualizada = service.actualizarModulo(id, moduloDTO);
			return new ResponseEntity<>(
					ApiResponse.<Modulo>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(moduloActualizada)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Modulo>builder()
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
	public ResponseEntity<Void>  eliminarModulo(@PathVariable Long id) {
		service.eliminarModulo(id);
		return ResponseEntity.noContent().build();
=======
	public ResponseEntity<ApiResponse<Void>>  eliminarModulo(@PathVariable Long id) {
		try {
			service.eliminarModulo(id);
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
