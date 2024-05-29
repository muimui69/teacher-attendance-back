package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teacherattendance.entity.Carrera;
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

	private String nombre;

	private String sigla;

	private Carrera carrera;

}
