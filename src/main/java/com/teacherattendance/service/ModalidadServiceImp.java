package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import com.teacherattendance.dto.ModalidadDTO;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Modalidad;
import com.teacherattendance.repository.ModalidadRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ModalidadServiceImp {
	
	@Autowired
	private ModalidadRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Modalidad> findAll(){
		List<Modalidad> modalidades =  repositorio.findAll();
		return modalidades;
	}
	
	public Modalidad guardarModalidad(ModalidadDTO modalidadDTO) {
		Modalidad modalidad = new Modalidad();
		modalidad.setNombre(modalidadDTO.getNombre());
		modalidad.setDescripcion(modalidadDTO.getDescripcion());
		return repositorio.save(modalidad);
	}
	
	public Optional<Modalidad>  obtenerModalidad(Long id) {
		Optional<Modalidad> modalidadOpt = repositorio.findById(id);
		if (!modalidadOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		return modalidadOpt;
	}
	
	public Modalidad actualizarModalidad(Long id,ModalidadDTO modalidadDTO) {
		Optional<Modalidad> modalidadOpt = repositorio.findById(id);
		if (!modalidadOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		modalidadDTO.setId(null);
		Modalidad modalidad = modalidadOpt.get();
		modalidad.setNombre(modalidadDTO.getNombre());
		modalidad.setDescripcion(modalidadDTO.getDescripcion());
        return repositorio.save(modalidad);
	}
	
	public void eliminarModalidad(Long id) {
		Optional<Modalidad> modalidadOpt = repositorio.findById(id);
		if (!modalidadOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		repositorio.delete(modalidadOpt.get());
	}

}
