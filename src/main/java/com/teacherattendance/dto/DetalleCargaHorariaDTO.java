package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class DetalleCargaHorariaDTO {

    private Long id;

    @NotNull(message = "El id del detalle carga horaria no puede ser nulo.")
    private Long aulaId;

    @NotNull(message = "La hora de inicio del detalle carga horaria  no puede ser nulo.")
    private LocalDateTime hora_inicio;

    @NotNull(message = "La hora de fin del detalle carga horaria  no puede ser nulo.")
    private LocalDateTime hora_fin;


    @NotNull(message = "El id_carga_horaria del detalle carga horaria no puede ser nulo.")
    private Long cargaHorariaId;
}
