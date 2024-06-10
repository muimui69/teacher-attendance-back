package com.teacherattendance.controllers;

import java.util.List;
<<<<<<< Updated upstream
=======
import java.util.Optional;
>>>>>>> Stashed changes
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
=======
import org.springframework.context.support.DefaultMessageSourceResolvable;
>>>>>>> Stashed changes
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
<<<<<<< Updated upstream
import com.teacherattendance.service.AsistenciaServiceImp;

@RestController
@RequestMapping("/api/asistencia")
=======
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.AsistenciaServiceImp;
import com.teacherattendance.util.HttpStatusMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asistencia")
>>>>>>> Stashed changes
@CrossOrigin(origins = "http://localhost:4200")
public class AsistenciaController {
	
	@Autowired
	private AsistenciaServiceImp service;
	
<<<<<<< Updated upstream
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<AsistenciaDTO>> listarAsistencia() {
		List<Asistencia> asistencias = service.findAll();
		List<AsistenciaDTO> asistenciaDTO = asistencias.stream()
				.map(asistencia -> modelMapper.map(asistencia, AsistenciaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(asistenciaDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public Asistencia guardarAsistencia(@RequestBody AsistenciaDTO asistencia) {
		return service.guardarAsistencia(asistencia);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AsistenciaDTO> obtenerAsistencia(@PathVariable Long id) {
		Asistencia asistencia =  service.obtenerAsistencia(id);
		AsistenciaDTO asistenciaDTO = modelMapper.map(asistencia, AsistenciaDTO.class);
		return  new ResponseEntity<>(asistenciaDTO, HttpStatus.OK);
	}

//	@PutMapping("/asistencia/{id}")
//	public ResponseEntity<Asistencia> actualizarAsistencia(@PathVariable Long id,@RequestBody Asistencia detalleAsistencia) {
//		Asistencia asistencia =  service.obtenerAsistencia(id);
//		asistencia.setId(detalleAsistencia.getId());
//		asistencia.setDocente(detalleAsistencia.getDocente());
//		asistencia.setEntrada(detalleAsistencia.getEntrada());
//		asistencia.setEstado(detalleAsistencia.getEstado());
//		asistencia.setHorasAtraso(detalleAsistencia.getHorasAtraso());
//		asistencia.setSalida(detalleAsistencia.getSalida());
//		asistencia.setToleranciaMinutos(detalleAsistencia.getToleranciaMinutos());
//		Asistencia asistenciaActualizado = service.actualizarAsistencia(asistencia);
//		return ResponseEntity.ok(asistenciaActualizado);
//	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<Map<String, Boolean>>  eliminarAsistencia(@PathVariable Long id) {
//		Asistencia asistencia =  service.obtenerAsistencia(id);
//		service.eliminarAsistencia(asistencia);
//		Map<String, Boolean> respuesta = new HashMap<>();
//		respuesta.put("eliminar", Boolean.TRUE);
//		return ResponseEntity.ok(respuesta);
//	}
=======
	
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
	public ResponseEntity<ApiResponse<Asistencia>>  obtenerAsistencia(@PathVariable Long id) {
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
>>>>>>> Stashed changes

}
