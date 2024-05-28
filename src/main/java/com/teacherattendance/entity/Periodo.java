package com.teacherattendance.entity;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Periodo {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@DateTimeFormat(iso = ISO.DATE)
	@Past
	@NotNull(message = "Debe ingresar la feha de inicio")
	private LocalDate fecha_inicio;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Past
	@NotNull(message = "Debe ingresar la feha del fin")
	private LocalDate fecha_fin;
	
	private int gestion;
	
	private String nombre;

}