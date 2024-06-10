package com.teacherattendance.dto;

<<<<<<< Updated upstream
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teacherattendance.entity.Aula;
import com.teacherattendance.entity.CargaHoraria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
=======
    @NotNull(message = "La hora de inicio del detalle carga horaria  no puede ser nulo.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime hora_inicio;

    @NotNull(message = "La hora de fin del detalle carga horaria  no puede ser nulo.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime hora_fin;


    @NotNull(message = "El id_carga_horaria del detalle carga horaria no puede ser nulo.")
    private Long cargaHorariaId;
    
    @NotNull(message = "El id_grupo del detalle carga horaria no puede ser nulo.")
    private Long grupoId;
>>>>>>> Stashed changes
}
