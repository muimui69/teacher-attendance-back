package com.teacherattendance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CargaHoraria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_Docente")
	private Usuarios docente;
	
	@ManyToOne
	@JoinColumn(name = "id_Materia")
	private Materia materia;
	
	@ManyToOne
	@JoinColumn(name = "id_Periodo")
	private Periodo periodo;
	
	@ManyToOne
	@JoinColumn(name = "id_Modalidad")
	private Modalidad modalidad;
	
}
