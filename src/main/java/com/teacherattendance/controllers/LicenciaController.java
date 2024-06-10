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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.teacherattendance.dto.LicenciaDTO;
import com.teacherattendance.entity.Licencia;
<<<<<<< Updated upstream
import com.teacherattendance.service.LicenciaServiceImp;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/licencia")
=======
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.LicenciaServiceImp;
import com.teacherattendance.util.HttpStatusMessage;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/licencia")
>>>>>>> Stashed changes
public class LicenciaController {
	
	@Autowired
	private LicenciaServiceImp service;
	
<<<<<<< Updated upstream
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<LicenciaDTO>> listarLicencia() {
		List<Licencia> licencias = service.findAll();
		List<LicenciaDTO> licenciaDTOs = licencias.stream()
				.map(licencia -> modelMapper.map(licencia, LicenciaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(licenciaDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Licencia> guardarLicencia(@Validated @RequestBody LicenciaDTO licenciaDto) throws Exception{
		return new ResponseEntity<>(service.guardarLicencia(licenciaDto), HttpStatus.OK); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<LicenciaDTO> obtenerLicencia(@PathVariable Long id) {
		Licencia licencia =  service.obtenerLicencia(id);
		LicenciaDTO licenciaDTO = modelMapper.map(licencia, LicenciaDTO.class);
		return ResponseEntity.ok(licenciaDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Licencia> actualizarLicencia(@PathVariable Long id,@RequestBody LicenciaDTO licenciaDTO) {
		return new ResponseEntity<>(service.actualizarLicencia(id, licenciaDTO), HttpStatus.OK); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarLicencia(@PathVariable Long id) {
		service.eliminarLicencia(id);
		return ResponseEntity.noContent().build();
=======
	@GetMapping
	public ResponseEntity<ApiResponse<List<Licencia>>> listarLicencias() {
		List<Licencia> licencias  = service.findAll();
		return new ResponseEntity<>(
				ApiResponse.<List<Licencia>>builder()
						.statusCode(HttpStatus.OK.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.OK))
						.data(licencias)
						.build(),
				HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Licencia>> guardarLicencia(@Valid @RequestBody LicenciaDTO licenciaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Licencia>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}
		Licencia licenciaCreada = service.guardarLicencia(licenciaDTO);
		return new ResponseEntity<>(
				ApiResponse.<Licencia>builder()
						.statusCode(HttpStatus.CREATED.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
						.data(licenciaCreada)
						.build(),
				HttpStatus.CREATED
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Licencia>> obtenerLicencia(@PathVariable Long id) {
		try {
			Optional<Licencia> licenciaOpt = service.obtenerLicencia(id);
			return new ResponseEntity<>(
					ApiResponse.<Licencia>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(licenciaOpt.get())
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Licencia>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Licencia>> actualizarLicencia(@PathVariable Long id, @Valid @RequestBody LicenciaDTO licenciaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Licencia>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}

		try {
			Licencia licenciaActualizada = service.actualizarLicencia(id, licenciaDTO);
			return new ResponseEntity<>(
					ApiResponse.<Licencia>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(licenciaActualizada)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Licencia>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>>  eliminarLicencia(@PathVariable Long id) {
		try {
			service.eliminarLicencia(id);
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
