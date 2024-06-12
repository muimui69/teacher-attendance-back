package com.teacherattendance.controllers;

import com.teacherattendance.dto.DiasDTO;
import com.teacherattendance.dto.GrupoDTO;
import com.teacherattendance.entity.Dias;
import com.teacherattendance.entity.Grupo;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.DiasServiceImp;
import com.teacherattendance.service.GrupoServiceImp;
import com.teacherattendance.util.HttpStatusMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dias")
public class DiasController {
    @Autowired
    private DiasServiceImp service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Dias>>> listarDias() {
        List<Dias> dias = service.findAll();
        return new ResponseEntity<>(
                ApiResponse.<List<Dias>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatusMessage.getMessage(HttpStatus.OK))
                        .data(dias)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Dias>> guardarDia(@Valid @RequestBody DiasDTO diasDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(
                    ApiResponse.<Dias>builder()
                            .errors(errors)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        Dias diaCreado = service.guardarDia(diasDTO);
        return new ResponseEntity<>(
                ApiResponse.<Dias>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(HttpStatusMessage.getMessage(HttpStatus.CREATED))
                        .data(diaCreado)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Dias>> obtenerDia(@PathVariable Long id) {
        try {
            Optional<Dias> diasOpt = service.obtenerDia(id);
            return new ResponseEntity<>(
                    ApiResponse.<Dias>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message(HttpStatusMessage.getMessage(HttpStatus.OK))
                            .data(diasOpt.get())
                            .build(),
                    HttpStatus.OK
            );
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    ApiResponse.<Dias>builder()
                            .statusCode(e.getStatusCode().value())
                            .message(e.getReason())
                            .build(),
                    e.getStatusCode()
            );
        }
    }

}
