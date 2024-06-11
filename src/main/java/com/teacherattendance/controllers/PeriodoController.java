package com.teacherattendance.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.teacherattendance.dto.PeriodoDTO;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.PeriodoServiceImp;
import com.teacherattendance.util.HttpStatusMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/periodo")
// @CrossOrigin(origins = "http://localhost:4200")
public class PeriodoController {
	
    @Autowired
    private PeriodoServiceImp service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Periodo>>> listarPeriodos() {
        List<Periodo> periodos = service.findAll();
        return new ResponseEntity<>(
                ApiResponse.<List<Periodo>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatusMessage.getMessage(HttpStatus.OK))
                        .data(periodos)
                        .build(),
                HttpStatus.OK
        );
    }

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
}
