package com.teacherattendance.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teacherattendance.entity.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsistenciaDto {
	
    private Long id;
	
	private int estado;
	
	private LocalDateTime entrada;

	private LocalDateTime salida;
	
    private int toleranciaMinutos;

    private Duration horasAtraso;
    
    private Usuarios docente;

}
