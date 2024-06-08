package com.teacherattendance.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeriodoDTO {

    private Long id;
    
	private int gestion;
	
	private String nombre;
	
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Debe ingresar la feha del inicio")
	private LocalDate fecha_inicio;
	
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Debe ingresar la feha del fin")
	private LocalDate fecha_fin;
	
}
