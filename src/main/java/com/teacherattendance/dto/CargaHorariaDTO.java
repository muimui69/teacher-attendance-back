package com.teacherattendance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargaHorariaDTO {
	
    private Long id;
	
    @NotNull(message = "El id del docente no puede ser nulo.")
	private Long id_docente;
	
	@NotNull(message = "El id de la materia no puede ser nulo.")
	private Long id_materia;
	
	@NotNull(message = "El id del periodo no puede ser nulo.")
	private Long id_periodo;
	
	@NotNull(message = "El id de la modalidad no puede ser nulo.")
	private Long id_modalidad;

}
