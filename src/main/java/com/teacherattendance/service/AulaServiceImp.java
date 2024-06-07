package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherattendance.dto.AulaDTO;
import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Aula;
import com.teacherattendance.repository.AulaRepository;

@Service
public class AulaServiceImp {
	
	@Autowired
	private AulaRepository repositorio;
	
	public List<Aula> findAll(){
		return repositorio.findAll();
	}
	
	public Aula guardarAula(AulaDTO aulaDto) {
		Aula aula = new Aula(aulaDto.getId(), aulaDto.getNombre(), aulaDto.getModulo());
		return repositorio.save(aula);
	}
	
	public Aula obtenerAulaPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Aula actualizarAula(Long id,AulaDTO aulaDTO) {
		Aula aula = obtenerAulaPorId(id);
		aula.setModulo(aulaDTO.getModulo());
		aula.setNombre(aulaDTO.getNombre());
		return repositorio.save(aula);
	}
	
	public void eliminarAula(Long id) {
		repositorio.deleteById(id);
	}

}
