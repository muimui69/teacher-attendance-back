package com.teacherattendance.controllers;

import java.util.List;
<<<<<<< Updated upstream
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
=======
import java.util.Optional;
import java.util.stream.Collectors;
import com.teacherattendance.dto.PeriodoDTO;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.PeriodoServiceImp;
import com.teacherattendance.util.HttpStatusMessage;
import jakarta.validation.Valid;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< Updated upstream
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teacherattendance.dto.PeriodoDTO;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.service.PeriodoServiceImp;

@RestController
@RequestMapping("/api/periodo")
=======
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/periodo")
>>>>>>> Stashed changes
@CrossOrigin(origins = "http://localhost:4200")
public class PeriodoController {
	
	@Autowired
	private PeriodoServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<PeriodoDTO>> listarPeriodos(){
		List<Periodo> periodos = service.findAll();
		List<PeriodoDTO> periodoDTOs = periodos.stream()
				.map(periodo -> modelMapper.map(periodo, PeriodoDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(periodoDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Periodo> guardarPeriodo(@Validated @RequestBody PeriodoDTO periodoDTO) throws Exception{
		return new ResponseEntity<>(service.guardarPeriodo(periodoDTO), HttpStatus.OK); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<PeriodoDTO> obtenerPeriodo(@PathVariable Long id) {
		Periodo periodo =  service.obtenerPeriodo(id);
		PeriodoDTO periodoDTO = modelMapper.map(periodo, PeriodoDTO.class);
		return ResponseEntity.ok(periodoDTO);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Periodo> actualizarPeriodo(@PathVariable Long id,@RequestBody PeriodoDTO periodoDTO) {
		return new ResponseEntity<>(service.actualizarPeriodo(id, periodoDTO), HttpStatus.OK);
	}

<<<<<<< Updated upstream
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> eliminarCargaHoraria(@PathVariable Long id) {
		service.eliminarPeriodo(id);
		return ResponseEntity.noContent().build();
	}

=======
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<Periodo>>  guardarPeriodo(@Valid  @RequestBody PeriodoDTO periodoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(
                    ApiResponse.<Periodo>builder()
                            .errors(errors)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        Periodo periodoCreado = service.guardarPeriodo(periodoDTO);
        return new ResponseEntity<>(
                ApiResponse.<Periodo>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
                        .data(periodoCreado)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Periodo>>  obtenerPeriodo(@PathVariable Long id) {
        try {
            Optional<Periodo> periodoOpt = service.obtenerPeriodo(id);
            return new ResponseEntity<>(
                    ApiResponse.<Periodo>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message(HttpStatusMessage.getMessage(HttpStatus.OK))
                            .data(periodoOpt.get())
                            .build(),
                    HttpStatus.OK
            );
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    ApiResponse.<Periodo>builder()
                            .statusCode(e.getStatusCode().value())
                            .message(e.getReason())
                            .build(),
                    e.getStatusCode()
            );
        }
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<Periodo>> actualizarPeriodo(@PathVariable Long id, @Valid @RequestBody PeriodoDTO periodoDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(
                    ApiResponse.<Periodo>builder()
                            .errors(errors)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            Periodo periodoActualizada = service.actualizarPeriodo(id, periodoDTO);
            return new ResponseEntity<>(
                    ApiResponse.<Periodo>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message(HttpStatusMessage.getMessage(HttpStatus.OK))
                            .data(periodoActualizada)
                            .build(),
                    HttpStatus.OK
            );
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    ApiResponse.<Periodo>builder()
                            .statusCode(e.getStatusCode().value())
                            .message(e.getReason())
                            .build(),
                    e.getStatusCode()
            );
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<Void>> eliminarPeriodo(@PathVariable Long id) {
        try {
            service.eliminarPeriodo(id);
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
