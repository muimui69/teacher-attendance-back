package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.repository.MateriaRepository;

@Service
public class MateriaServiceImp {
	
	@Autowired
	private MateriaRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Materia> findAll(){
		return (List<Materia>) repositorio.findAll();
	}
	
	public Materia guardarMateria(Materia materia) {
		return repositorio.save(materia);
	}
	
	public Materia obtenerMateria(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Materia actualizarMateria(Materia materia) {
		return repositorio.save(materia);
	}
	
	public void eliminarMateria(Materia materia) {
		repositorio.delete(materia);
	}

}
