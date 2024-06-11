package com.teacherattendance.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.teacherattendance.util.FutureOrPresentDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
public class Licencia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String titulo;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_Docente")
	private Usuarios docente;
	
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Debe ingresar la fecha para que la solicita la licencia")
	@FutureOrPresentDate(message = "La fecha debe ser hoy o en el futuro")
	private LocalDate fecha;

}
