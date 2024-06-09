package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MateriaDTO {

	private Long id;

	@NotNull(message = "El nombre de la materia no puede ser nulo.")
	@NotEmpty(message = "El nombre de la materia no puede estar vacio.")
	private String nombre;

	@NotNull(message = "La sigla de la materia no puede ser nulo.")
	@NotEmpty(message = "La sigla de la materia no puede estar vacio.")
	private String sigla;

	@NotNull(message = "El id de la carrera no puede ser nulo.")
	private Long carreraId;

}

