package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.DetalleCargaHorariaDTO;
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
	
	public DetalleCargaHoraria guardarDetalleCargaHoraria(DetalleCargaHorariaDTO detalleCargaHorariaDTO) {
		DetalleCargaHoraria detalleCargaHoraria = new DetalleCargaHoraria(detalleCargaHorariaDTO.getId(),
				detalleCargaHorariaDTO.getCargaHoraria(), detalleCargaHorariaDTO.getAula(),
				detalleCargaHorariaDTO.getInicio(), detalleCargaHorariaDTO.getFin());
		return repositorio.save(detalleCargaHoraria);
	}
	
	public DetalleCargaHoraria obtenerDetalleCargaHoraria(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public DetalleCargaHoraria actualizarDetalleCargaHoraria(Long id, DetalleCargaHorariaDTO detalleCargaHorariaDTO) {
		DetalleCargaHoraria detalleCargaHoraria = obtenerDetalleCargaHoraria(id);
		detalleCargaHoraria.setCargaHoraria(detalleCargaHorariaDTO.getCargaHoraria());
		detalleCargaHoraria.setAula(detalleCargaHorariaDTO.getAula());
		detalleCargaHoraria.setInicio(detalleCargaHorariaDTO.getInicio());
		detalleCargaHoraria.setFin(detalleCargaHorariaDTO.getFin());
		return repositorio.save(detalleCargaHoraria);
	}
	
	public void eliminarDetalleCargaHoraria(Long id) {
		repositorio.deleteById(id);
	}

}
