package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.repository.PeriodoRepository;

@Service
public class PeriodoServiceImp {
	
	@Autowired
	private PeriodoRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Periodo> findAll(){
		return (List<Periodo>) repositorio.findAll();
	}
	
	public Periodo guardarPeriodo(Periodo periodo) {
		return repositorio.save(periodo);
	}
	
	public Periodo obtenerPeriodo(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Periodo actualizarPeriodo(Periodo periodo) {
		return repositorio.save(periodo);
	}
	
	public void eliminarPeriodo(Periodo periodo) {
		repositorio.delete(periodo);
	}

}
