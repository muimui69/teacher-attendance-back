package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.repository.CarreraRepository;

@Service
public class CarreraServiceImp {
	
	@Autowired
	private CarreraRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Carrera> findAll(){
		return (List<Carrera>) repositorio.findAll();
	}
	
	public Carrera guardarCarrera(CarreraDTO carreraDTO) {
		Carrera carrera = new Carrera(carreraDTO.getId(), carreraDTO.getNombre());
		return repositorio.save(carrera);
	}
	
	public Carrera obtenerCarrera(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Carrera actualizarCarrera(Carrera carrera) {
		return repositorio.save(carrera);
	}
	
	public void eliminarCarrera(Carrera carrera) {
		repositorio.delete(carrera);
	}

}
