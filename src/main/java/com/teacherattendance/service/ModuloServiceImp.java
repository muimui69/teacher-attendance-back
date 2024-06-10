package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import com.teacherattendance.dto.ModuloDTO;
import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.entity.Modulo;
import com.teacherattendance.repository.ModuloRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ModuloServiceImp {
	
	@Autowired
	private ModuloRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Modulo> findAll(){
		List<Modulo> modulos = repositorio.findAll();
		return modulos;
	}
	
	public Modulo guardarModulo(ModuloDTO moduloDTO) {
		Modulo modulo = new Modulo();
		modulo.setUbicacion(moduloDTO.getUbicacion());
		modulo.setNumero(moduloDTO.getNumero());
		return repositorio.save(modulo);
	}
	
	public Optional<Modulo> obtenerModulo(Long id) {
		Optional<Modulo> moduloOpt = repositorio.findById(id);
		if (!moduloOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		return moduloOpt;
	}
	
	public Modulo actualizarModulo(Long id, ModuloDTO moduloDTO) {
		Optional<Modulo> moduloOpt = repositorio.findById(id);
		if (!moduloOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}

		moduloDTO.setId(null);
		Modulo modulo = moduloOpt.get();
		modulo.setUbicacion(moduloDTO.getUbicacion());
		modulo.setNumero(moduloDTO.getNumero());
		return repositorio.save(modulo);
	}
	
	public void eliminarModulo(Long id) {
		Optional<Modulo> moduloOpt = repositorio.findById(id);
		if (!moduloOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		repositorio.delete(moduloOpt.get());
	}

}
