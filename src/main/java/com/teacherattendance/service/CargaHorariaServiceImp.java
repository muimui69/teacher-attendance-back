package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.CargaHoraria;
import com.teacherattendance.repository.CargaHorariaRepository;

@Service
public class CargaHorariaServiceImp {

	@Autowired
	private CargaHorariaRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<CargaHoraria> findAll() {
		return (List<CargaHoraria>) repositorio.findAll();
	}

	public CargaHoraria guardarCargaHoraria(CargaHoraria cargaHoraria) {
		return repositorio.save(cargaHoraria);
	}

	public CargaHoraria obtenerCargaHorariaPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}

	public CargaHoraria actualizarCargaHoraria(CargaHoraria cargaHoraria) {
		return repositorio.save(cargaHoraria);
	}

	public void eliminarCargaHoraria(CargaHoraria cargaHoraria) {
		repositorio.delete(cargaHoraria);

	}

	
}
