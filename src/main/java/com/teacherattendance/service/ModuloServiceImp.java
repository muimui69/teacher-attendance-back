package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Modulo;
import com.teacherattendance.repository.ModuloRepository;

@Service
public class ModuloServiceImp {
	
	@Autowired
	private ModuloRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Modulo> findAll(){
		return (List<Modulo>) repositorio.findAll();
	}
	
	public Modulo guardarModulo(Modulo carrera) {
		return repositorio.save(carrera);
	}
	
	public Modulo obtenerModulo(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Modulo actualizarModulo(Modulo carrera) {
		return repositorio.save(carrera);
	}
	
	public void eliminarModulo(Modulo modulo) {
		repositorio.delete(modulo);
	}

}
