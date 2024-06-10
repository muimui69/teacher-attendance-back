package com.teacherattendance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.service.CarreraServiceImp;

@RestController
<<<<<<< Updated upstream
@RequestMapping("/api/carrera")
=======
@RequestMapping("/carrera")
>>>>>>> Stashed changes
@CrossOrigin(origins = "http://localhost:4200")
public class CarreraController {
	
	@Autowired
	private CarreraServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<CarreraDTO>> listarCarrera() {
		List<Carrera> carrera = service.findAll();
		List<CarreraDTO> carreraDTO = carrera.stream()
				.map(carreras -> modelMapper.map(carreras, CarreraDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(carreraDTO, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public Carrera guardarCarrera(@RequestBody CarreraDTO carreraDTO) {
		return service.guardarCarrera(carreraDTO);
=======
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
>>>>>>> Stashed changes
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarreraDTO> obtenerCarrera(@PathVariable Long id) {
		Carrera carrera =  service.obtenerCarrera(id);
		CarreraDTO carreraDTO = modelMapper.map(carrera, CarreraDTO.class);
		return new ResponseEntity<>(carreraDTO, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public ResponseEntity<CarreraDTO> actualizarCarrera(@PathVariable Long id,@RequestBody CarreraDTO detalleCarrera) {
		Carrera carreraActualizado = service.actualizarCarrera(id, detalleCarrera);
		CarreraDTO carreraDTO = modelMapper.map(carreraActualizado, CarreraDTO.class);
		return ResponseEntity.ok(carreraDTO);
=======
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
>>>>>>> Stashed changes
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
<<<<<<< Updated upstream
	public ResponseEntity<Void> eliminarCarrera(@PathVariable Long id) {
		service.eliminarCarrera(id);
		return ResponseEntity.noContent().build();
=======
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
>>>>>>> Stashed changes
	}

}
