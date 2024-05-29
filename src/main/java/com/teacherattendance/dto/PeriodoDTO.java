package com.teacherattendance.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeriodoDTO {

    private Long id;

	private LocalDate fecha_inicio;

	private LocalDate fecha_fin;
	
	private int gestion;
	
	private String nombre;
	
}
