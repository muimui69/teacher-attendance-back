package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AulaDTO {
	
    private Long id;

	@NotNull(message = "El numero o nombre de la aula no puede ser nulo.")
	private int nombre;

	@NotNull(message = "El id de la carrera no puede ser nulo.")
	private Long moduloId;
	

}
