package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.AsistenciaDto;
import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Asistencia;
import com.teacherattendance.repository.AsistenciaRepository;

@Service
public class AsistenciaServiceImp {
	
	@Autowired
	private AsistenciaRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Asistencia> findAll(){
		return (List<Asistencia>) repositorio.findAll();
	}
	
	public Asistencia guardarAsistencia(AsistenciaDto asistenciaDto) {
		Asistencia asistencia = new Asistencia(asistenciaDto.getId(), asistenciaDto.getEstado(), 
				asistenciaDto.getEntrada(), asistenciaDto.getSalida(), asistenciaDto.getToleranciaMinutos(), 
				asistenciaDto.getHorasAtraso(), asistenciaDto.getDocente());
		return repositorio.save(asistencia);
	}
	
	public Asistencia obtenerAsistencia(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Asistencia actualizarAsistencia(Asistencia asistencia) {
		return repositorio.save(asistencia);
	}
	
	public void eliminarAsistencia(Asistencia asistencia) {
		repositorio.delete(asistencia);
	}

}
