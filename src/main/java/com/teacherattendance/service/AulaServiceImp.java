package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import com.teacherattendance.entity.Modulo;
import com.teacherattendance.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.teacherattendance.dto.AulaDTO;
import com.teacherattendance.entity.Aula;
import com.teacherattendance.repository.AulaRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AulaServiceImp {
	
	@Autowired
	private AulaRepository repositorio;

	@Autowired
	private ModuloServiceImp moduloService;
	
	public List<Aula> findAll(){
		List<Aula> aulas = repositorio.findAll();
		return aulas;
	}
	
	public Aula guardarAula(AulaDTO aulaDto) {
		Optional<Modulo> moduloOpt = moduloService.obtenerModulo(aulaDto.getModuloId());
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
		Optional<Aula> aulaOpt = obtenerAula(id);
		Optional<Modulo> moduloOpt = moduloService.obtenerModulo(aulaDto.getModuloId());
		Aula aula = aulaOpt.get();
		aula.setNombre(aulaDto.getNombre());
		aula.setModulo(moduloOpt.get());
		return repositorio.save(aula);
	}
	
	public void eliminarAula(Long id) {
		Optional<Aula> aulaOpt = obtenerAula(id);
		repositorio.delete(aulaOpt.get());
	}

}
