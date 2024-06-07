package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO  {
	
	private Long id;

    @NotEmpty(message = "ingrese un name")
    private String nombre;

    @NotEmpty(message = "ingrese un apellido")
    private String apellido;
    
    private String email;
    
    private String password;
    
}
