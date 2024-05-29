package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.AulaDto;
import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Aula;
import com.teacherattendance.repository.AulaRepository;

@Service
public class AulaServiceImp {
	
	@Autowired
	private AulaRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Aula> findAll(){
		return (List<Aula>) repositorio.findAll();
	}
	
	public Aula guardarAula(AulaDto aulaDto) {
		Aula aula = new Aula(aulaDto.getId(), aulaDto.getNombre(), aulaDto.getModulo());
		return repositorio.save(aula);
	}
	
	public Aula obtenerAulaPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Aula actualizarAula(Aula aula) {
		return repositorio.save(aula);
	}
	
	public void eliminarAula(Aula aula) {
		repositorio.delete(aula);
	}

}
