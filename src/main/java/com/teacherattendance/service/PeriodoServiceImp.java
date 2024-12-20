package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.teacherattendance.dto.PeriodoDTO;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.repository.PeriodoRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PeriodoServiceImp {
	
	@Autowired
	private PeriodoRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Periodo> findAll(){
		List<Periodo> periodos = repositorio.findAll();
		return periodos;
	}

	public Periodo guardarPeriodo(PeriodoDTO periodoDTO) {
		Periodo periodo = new Periodo();
		periodo.setNombre(periodoDTO.getNombre());
		periodo.setGestion(periodoDTO.getGestion());
		periodo.setFecha_inicio(periodoDTO.getFecha_inicio());
		periodo.setFecha_fin(periodoDTO.getFecha_fin());
		return repositorio.save(periodo);
	}


	public Optional<Periodo> obtenerPeriodo(Long id) {
		Optional<Periodo> periodoOpt = repositorio.findById(id);
		if (!periodoOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "No existe el periodo con el id " + id
			);
		}
		return periodoOpt;
	}

	public Periodo actualizarPeriodo(Long id, PeriodoDTO periodoDTO){
		Optional<Periodo> periodoOpt = obtenerPeriodo(id);
		Periodo periodo = periodoOpt.get();
		periodo.setNombre(periodoDTO.getNombre());
		periodo.setGestion(periodoDTO.getGestion());
		periodo.setFecha_inicio(periodoDTO.getFecha_inicio());
		periodo.setFecha_fin(periodoDTO.getFecha_fin());
		return repositorio.save(periodo);
	}


	public void eliminarPeriodo(Long id) {
		Optional<Periodo> periodoOpt = obtenerPeriodo(id);
		repositorio.delete(periodoOpt.get());
	}

}
