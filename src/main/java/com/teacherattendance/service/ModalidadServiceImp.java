package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.ModalidadDTO;
import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Modalidad;
import com.teacherattendance.repository.ModalidadRepository;

@Service
public class ModalidadServiceImp {
	
	@Autowired
	private ModalidadRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Modalidad> findAll(){
		return repositorio.findAll();
	}
	
	public Modalidad guardarModalidad(ModalidadDTO modalidadDTO) {
		Modalidad modalidad = new Modalidad(modalidadDTO.getId(), modalidadDTO.getNombre(), modalidadDTO.getDescripcion());
		return repositorio.save(modalidad);
	}
	
	public Modalidad obtenerModalidad(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Modalidad actualizarModalidad(Long id, ModalidadDTO modalidadDTO) {
		Modalidad modalidad = obtenerModalidad(id);
		modalidad.setNombre(modalidadDTO.getNombre());
		modalidad.setDescripcion(modalidadDTO.getDescripcion());
		return repositorio.save(modalidad);
	}
	
	public void eliminarModalidad(Long id) {
		repositorio.deleteById(id);
	}

}
