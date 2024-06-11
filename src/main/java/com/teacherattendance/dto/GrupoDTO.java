package com.teacherattendance.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoDTO {
	
    private Long id;
	
	@NotNull(message = "El nombre del grupo no puede ser nulo.")
    @NotEmpty(message = "El nombre del grupo no puede estar vacio.")
	private String nombre; 

}
