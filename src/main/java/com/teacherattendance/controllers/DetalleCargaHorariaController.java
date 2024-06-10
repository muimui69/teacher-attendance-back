package com.teacherattendance.controllers;

import com.teacherattendance.dto.DetalleCargaHorariaDTO;
import com.teacherattendance.entity.DetalleCargaHoraria;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.DetalleCargaHorariaServiceImp;
import com.teacherattendance.util.HttpStatusMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detalle_carga_horaria")
public class DetalleCargaHorariaController {

    @Autowired
    private DetalleCargaHorariaServiceImp service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DetalleCargaHoraria>>> listarMaterias() {
        List<DetalleCargaHoraria> detalleeCargasHorarias = service.findAll();
        return new ResponseEntity<>(
                ApiResponse.<List<DetalleCargaHoraria>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatusMessage.getMessage(HttpStatus.OK))
                        .data(detalleeCargasHorarias)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DetalleCargaHoraria>>  guardarDetalleCargaHoraria(@Valid @RequestBody DetalleCargaHorariaDTO detalleCargaHorariaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(
                    ApiResponse.<DetalleCargaHoraria>builder()
                            .errors(errors)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        DetalleCargaHoraria detalleCargaHorariaCreada = service.guardarDetalleCargaHoraria(detalleCargaHorariaDTO);
        return new ResponseEntity<>(
                ApiResponse.<DetalleCargaHoraria>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
                        .data(detalleCargaHorariaCreada)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DetalleCargaHoraria>>  obtenerDetalleCargaHoraria(@PathVariable Long id) {
        try {
            Optional<DetalleCargaHoraria> detalleCargaHorariaOpt = service.obtenerDetalleCargaHoraria(id);
            return new ResponseEntity<>(
                    ApiResponse.<DetalleCargaHoraria>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message(HttpStatusMessage.getMessage(HttpStatus.OK))
                            .data(detalleCargaHorariaOpt.get())
                            .build(),
                    HttpStatus.OK
            );
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    ApiResponse.<DetalleCargaHoraria>builder()
                            .statusCode(e.getStatusCode().value())
                            .message(e.getReason())
                            .build(),
                    e.getStatusCode()
            );
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DetalleCargaHoraria>> actualizarDetalleCargaHoraria(@PathVariable Long id, @Valid @RequestBody DetalleCargaHorariaDTO detalleCargaHorariaDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(
                    ApiResponse.<DetalleCargaHoraria>builder()
                            .errors(errors)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            DetalleCargaHoraria detalleCargaHorariaActualizada = service.actualizarDetalleCargaHoraria(id, detalleCargaHorariaDTO);
            return new ResponseEntity<>(
                    ApiResponse.<DetalleCargaHoraria>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message(HttpStatusMessage.getMessage(HttpStatus.OK))
                            .data(detalleCargaHorariaActualizada)
                            .build(),
                    HttpStatus.OK
            );
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    ApiResponse.<DetalleCargaHoraria>builder()
                            .statusCode(e.getStatusCode().value())
                            .message(e.getReason())
                            .build(),
                    e.getStatusCode()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarDetalleCargaHoraria(@PathVariable Long id) {
        try {
            service.eliminarDetalleCargaHoraria(id);
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
