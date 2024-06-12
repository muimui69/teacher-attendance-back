package com.teacherattendance.auth;

import com.teacherattendance.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	
	String token;
	String email;
	Roles role;
	 String nombre;
	 String apellido;
	Long id;
}
