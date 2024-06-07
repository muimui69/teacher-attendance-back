package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.PeriodoDTO;
import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.repository.PeriodoRepository;

@Service
public class PeriodoServiceImp {
	
	@Autowired
	private PeriodoRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Periodo> findAll(){
		return repositorio.findAll();
	}
	
	public Periodo guardarPeriodo(PeriodoDTO periodoDTO) {
		Periodo periodo = new Periodo(periodoDTO.getId(), periodoDTO.getGestion(), periodoDTO.getNombre(), 
				periodoDTO.getFecha_inicio(), periodoDTO.getFecha_fin());
		return repositorio.save(periodo);
	}
	
	public Periodo obtenerPeriodo(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Periodo actualizarPeriodo(Long id, PeriodoDTO periodoDTO) {
		Periodo periodo = obtenerPeriodo(id);
		periodo.setGestion(periodoDTO.getGestion());
		periodo.setNombre(periodoDTO.getNombre());
		periodo.setFecha_inicio(periodoDTO.getFecha_inicio());
		periodo.setFecha_fin(periodoDTO.getFecha_fin());
		return repositorio.save(periodo);
	}
	
	public void eliminarPeriodo(Long id) {
		repositorio.deleteById(id);
	}

}
