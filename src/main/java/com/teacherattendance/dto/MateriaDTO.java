package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MateriaDTO {

	@NotNull(message = "El id de la materia no puede ser nulo.")
	private Long id;

	@NotNull(message = "El nombre de la carrera no puede ser nulo.")
	@NotEmpty(message = "El nombre de la carrera no puede estar vacio.")
	private String nombre;

	@NotNull(message = "El nombre de la carrera no puede ser nulo.")
	@NotEmpty(message = "El nombre de la carrera no puede estar vacio.")
	private String sigla;

	@NotNull(message = "El id de la carrera no puede ser nulo.")
	@Min(value = 1, message = "El id de la carrera debe ser mayor o igual a 1.")
	private Long carreraId;

}

