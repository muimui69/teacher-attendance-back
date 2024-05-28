package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.DetalleCargaHoraria;
import com.teacherattendance.repository.DCargaHorariaRepository;

@Service
public class DCargaHorariaServiceImp {
	
	@Autowired
	private DCargaHorariaRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<DetalleCargaHoraria> findAll(){
		return (List<DetalleCargaHoraria>) repositorio.findAll();
	}
	
	public DetalleCargaHoraria guardarDetalleCargaHoraria(DetalleCargaHoraria detalleCargaHoraria) {
		return repositorio.save(detalleCargaHoraria);
	}
	
	public DetalleCargaHoraria obtenerDetalleCargaHoraria(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public DetalleCargaHoraria actualizarDetalleCargaHoraria(DetalleCargaHoraria detalleCargaHoraria) {
		return repositorio.save(detalleCargaHoraria);
	}
	
	public void eliminarDetalleCargaHoraria(DetalleCargaHoraria detalleCargaHoraria) {
		repositorio.delete(detalleCargaHoraria);
	}

}
