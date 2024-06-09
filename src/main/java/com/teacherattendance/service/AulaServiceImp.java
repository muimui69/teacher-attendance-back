package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import com.teacherattendance.entity.Modulo;
import com.teacherattendance.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.AulaDTO;
import com.teacherattendance.entity.Aula;
import com.teacherattendance.repository.AulaRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AulaServiceImp {
	
	@Autowired
	private AulaRepository repositorio;

	@Autowired
	private ModuloRepository moduloRepository;
	
	@Transactional(readOnly = true)
	public List<Aula> findAll(){
		List<Aula> aulas = repositorio.findAll();
		return aulas;
	}
	
	public Aula guardarAula(AulaDTO aulaDto) {
		Optional<Modulo> moduloOpt = moduloRepository.findById(aulaDto.getModuloId());
		if (!moduloOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "No existe el modulo con el id " + aulaDto.getModuloId()
			);
		}
		Aula aula = new Aula();
		aula.setNombre(aulaDto.getNombre());
		aula.setModulo(moduloOpt.get());
		return repositorio.save(aula);
	}
	
	public Optional<Aula> obtenerAula(Long id) {
		Optional<Aula> aulaOpt = repositorio.findById(id);
		if (!aulaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "No existe el aula con el id " + id
			);
		}
		return aulaOpt;
	}
	
	public Aula actualizarAula(Long id, AulaDTO aulaDto) {
		Optional<Aula> aulaOpt = repositorio.findById(id);
		if (!aulaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"No existe el aula con el id " + id
			);
		}

		Optional<Modulo> moduloOpt = moduloRepository.findById(aulaDto.getModuloId());
		if (!moduloOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "No existe el modulo con el id " + aulaDto.getModuloId()
			);
		}

		aulaDto.setId(null);
		Aula aula = aulaOpt.get();
		aula.setNombre(aulaDto.getNombre());
		aula.setModulo(moduloOpt.get());
		return repositorio.save(aula);
	}
	
	public void eliminarAula(Long id) {
		Optional<Aula> aulaOpt = repositorio.findById(id);
		if (!aulaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "No existe el aula con el id " + id
			);
		}
		repositorio.delete(aulaOpt.get());
	}

}
