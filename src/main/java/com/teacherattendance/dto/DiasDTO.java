package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiasDTO {
    private Long id;

    @NotNull(message = "El nombre del dia no puede ser nulo.")
    @NotEmpty(message = "El nombre del dia no puede estar vacio.")
    private String nombre;
}