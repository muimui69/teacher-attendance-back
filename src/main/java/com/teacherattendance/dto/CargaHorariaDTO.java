package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.entity.Modalidad;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.entity.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CargaHorariaDTO {
	
    private Long id;
	
	private Usuarios docente;
	
	private Materia materia;
	
	private Periodo periodo;
	
	private Modalidad modalidad;

}
