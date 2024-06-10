package com.teacherattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO  {
	
	private Long id;

    @NotEmpty(message = "ingrese un nombre")
    private String nombre;

    @NotEmpty(message = "ingrese un apellido")
    private String apellido;
    
    @Email(message = "El email debe ser válido")
    private String email;
    
    @NotEmpty(message = "ingrese su contraseña")
    private String password;
    
}
