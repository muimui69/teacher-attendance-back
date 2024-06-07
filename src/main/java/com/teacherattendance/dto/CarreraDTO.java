package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarreraDTO {

    private Long id;

    @NotNull(message = "El nombre de la carrera no puede ser nulo.")
    @NotEmpty(message = "El nombre de la carrera no puede estar vacio.")
    @Pattern(regexp = "^[^\\d]*$", message = "El nombre de la carrera no debe contener números.")
	private String nombre;

}
