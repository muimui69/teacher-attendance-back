package com.teacherattendance.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.teacherattendance.util.FutureOrPresentDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenciaDTO {
	
    private Long id;
	
	private String titulo;
	
	@NotEmpty(message = "El motivo por el que solicita la licencia no debe estar vacio")
	private String descripcion;
	
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Debe ingresar la fecha para que la solicita la licencia")
	@FutureOrPresentDate(message = "La fecha debe ser hoy o en el futuro")
	private LocalDate fecha;

}
