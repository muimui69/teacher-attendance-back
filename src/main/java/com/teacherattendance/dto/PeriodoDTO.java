package com.teacherattendance.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.teacherattendance.util.FechaInicioAntesDeFechaFin;
import com.teacherattendance.util.FechaInicioAntesDeFechaFinValidator;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PeriodoDTO {

    private Long id;

	@NotNull(message = "La fecha de inicio no puede ser nula.")
	private LocalDate fecha_inicio;


	@NotNull(message = "La fecha de fin no puede ser nula.")
	private LocalDate fecha_fin;

	@NotNull(message = "La gestion del periodo no puede ser nulo.")
	private int gestion;

	@NotNull(message = "El nombre del periodo no puede ser nulo.")
	@NotEmpty(message = "El nombre del periodo no puede estar vacio.")
	private String nombre;
	
}
