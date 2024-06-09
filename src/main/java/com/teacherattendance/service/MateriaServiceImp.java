package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.repository.CarreraRepository;
import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.dto.MateriaDTO;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.repository.MateriaRepository;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@Service
public class MateriaServiceImp {
	
	@Autowired
	private MateriaRepository repositorio;

	@Autowired
	private CarreraRepository carreraRepository;
	
	@Transactional(readOnly = true)
	public List<Materia> findAll(){
        List<Materia> materias = repositorio.findAll();
		return materias;
	}

	public Materia guardarMateria(MateriaDTO materiaDTO) {
		Optional<Carrera> carreraOpt = carreraRepository.findById(materiaDTO.getCarreraId());
		if (!carreraOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		Materia materia = new Materia();
		materia.setNombre(materiaDTO.getNombre());
		materia.setSigla(materiaDTO.getSigla());
		materia.setCarrera(carreraOpt.get());
		return repositorio.save(materia);
	}



	public Optional<Materia> obtenerMateria(Long id) {
		Optional<Materia> materiaOpt = repositorio.findById(id);
		if (!materiaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		return materiaOpt;
	}
	
	public Materia actualizarMateria(Long id, MateriaDTO materiaDTO){
		Optional<Materia> materiaOpt = repositorio.findById(id);
		if (!materiaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "No existe la materia con el id " + materiaDTO.getId()
			);
		}

		Optional<Carrera> carreraOpt = carreraRepository.findById(materiaDTO.getCarreraId());
		if (!carreraOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "No existe la carrera con el id " + materiaDTO.getCarreraId()
			);
		}

		materiaDTO.setId(null);
		Materia materia = materiaOpt.get();
		materia.setNombre(materiaDTO.getNombre());
		materia.setSigla(materiaDTO.getSigla());
		materia.setCarrera(carreraOpt.get());
		return repositorio.save(materia);
	}


	public void eliminarMateria(Long id) {
		Optional<Materia> materiaOpt = repositorio.findById(id);
		if (!materiaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		repositorio.delete(materiaOpt.get());
	}


}
