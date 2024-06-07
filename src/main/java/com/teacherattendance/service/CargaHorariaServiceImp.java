package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.CargaHorariaDTO;
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

	public CargaHoraria guardarCargaHoraria(CargaHorariaDTO cargaHorariaDTO) {
		CargaHoraria cargaHoraria = new CargaHoraria(cargaHorariaDTO.getId(), cargaHorariaDTO.getDocente(),
				cargaHorariaDTO.getMateria(), cargaHorariaDTO.getPeriodo(), cargaHorariaDTO.getModalidad());
		return repositorio.save(cargaHoraria);
	}

	public CargaHoraria obtenerCargaHorariaPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}

	public CargaHoraria actualizarCargaHoraria(Long id, CargaHorariaDTO cargaHorariaDTO) {
		CargaHoraria cargaHoraria = obtenerCargaHorariaPorId(id);
		cargaHoraria.setDocente(cargaHorariaDTO.getDocente());
		cargaHoraria.setMateria(cargaHorariaDTO.getMateria());
		cargaHoraria.setModalidad(cargaHorariaDTO.getModalidad());
		cargaHoraria.setPeriodo(cargaHorariaDTO.getPeriodo());
		return repositorio.save(cargaHoraria);
	}

	public void eliminarCargaHoraria(Long id) {
		repositorio.deleteById(id);

	}

	
}
