package com.teacherattendance.entity;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

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
public class DetalleCargaHoraria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_carga_horaria")
	private CargaHoraria cargaHoraria;
	
	@ManyToOne
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;

	@ManyToOne
	@JoinColumn(name = "id_aula")
	private Aula aula;

	@ManyToOne
	@JoinColumn(name = "id_dia")
	private Dias dias;

	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime hora_inicio;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime hora_fin;

	
}
