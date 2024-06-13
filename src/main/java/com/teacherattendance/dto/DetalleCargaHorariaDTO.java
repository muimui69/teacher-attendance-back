package com.teacherattendance.dto;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetalleCargaHorariaDTO {

    private Long id;

    @NotNull(message = "El id del detalle carga horaria no puede ser nulo.")
    private Long aulaId;

    @NotNull(message = "La hora de inicio del detalle carga horaria  no puede ser nulo.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime hora_inicio;

    @NotNull(message = "La hora de fin del detalle carga horaria  no puede ser nulo.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime hora_fin;

    @NotNull(message = "El id_carga_horaria del detalle carga horaria no puede ser nulo.")
    private Long cargaHorariaId;
    
    @NotNull(message = "El id_dia del detalle carga horaria no puede ser nulo.")
    private Long diaId;
    
    @NotNull(message = "El id_grupo del detalle carga horaria no puede ser nulo.")
    private Long grupoId;

}
