package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teacherattendance.entity.Modulo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModalidadDTO {

    private Long id;

	@NotNull(message = "El nombre de la modalidad no puede ser nulo.")
	@NotEmpty(message = "El nombre de la modalidad no puede estar vacio.")
	private String nombre;

	@NotNull(message = "La descripcion de la modalidad no puede ser nulo.")
	@NotEmpty(message = "La descripcion de la modalidad no puede estar vacio.")
	private String descripcion;


}
