package com.teacherattendance.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodoDTO {

    private Long id;

    @DateTimeFormat(iso = ISO.DATE)
    @NotNull(message = "Debe ingresar la feha del inicio")
    private LocalDate fecha_inicio;

    @DateTimeFormat(iso = ISO.DATE)
    @NotNull(message = "Debe ingresar la feha del fin")
    private LocalDate fecha_fin;

	@NotNull(message = "La gestion del periodo no puede ser nulo.")
	private int gestion;

	@NotNull(message = "El nombre del periodo no puede ser nulo.")
	@NotEmpty(message = "El nombre del periodo no puede estar vacio.")
	private String nombre;
	
	
}
