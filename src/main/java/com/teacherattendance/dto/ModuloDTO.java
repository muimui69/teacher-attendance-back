package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuloDTO {
	
    private Long id;

    @NotNull(message = "La ubicacion de la modulo no puede ser nulo.")
    @NotEmpty(message = "La ubicacion de la modulo no puede estar vacio.")
    private String ubicacion;

    @NotNull(message = "El numero del modulo no puede ser nulo.")
    @Min( value = 1, message = "El numero del modulo debe ser positivo")
    private int numero;
   

}
