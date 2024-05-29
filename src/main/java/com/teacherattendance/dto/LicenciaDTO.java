package com.teacherattendance.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teacherattendance.entity.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenciaDTO {
	
    private Long id;
	
	private String titulo;
	
	private String descripcion;

	private Usuarios docente;
	
	private LocalDate fecha;

}
