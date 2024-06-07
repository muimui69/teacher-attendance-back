package com.teacherattendance.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teacherattendance.entity.Aula;
import com.teacherattendance.entity.CargaHoraria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetalleCargaHorariaDTO {
	
    private Long id;
	
	private CargaHoraria cargaHoraria;

	private Aula aula;
	
	private LocalDateTime inicio;
	
	private LocalDateTime fin;

}
