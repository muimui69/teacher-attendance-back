package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;
import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.repository.CarreraRepository;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CarreraServiceImp {
	
	@Autowired
	private CarreraRepository repositorio;

	@Transactional(readOnly = true)
	public List<Carrera> findAll() {
		List<Carrera> carreras = (List<Carrera>) repositorio.findAll();
		return carreras;
	}

	public Carrera guardarCarrera(CarreraDTO carreraDTO) {
		Carrera carrera = new Carrera();
		carrera.setNombre(carreraDTO.getNombre());
		carrera.setId(carreraDTO.getId());
		return repositorio.save(carrera);
	}

	public Optional<Carrera> obtenerCarrera(Long id) {
		Optional<Carrera> carreraOpt = repositorio.findById(id);
		if (!carreraOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"No existe la carrera con el id " + id
			);
		}
		return carreraOpt;
	}

	public Carrera actualizarCarrera(Long id, CarreraDTO carreraDTO) {
		Optional<Carrera> carreraOpt = obtenerCarrera(id);
		carreraDTO.setId(null);
		Carrera carrera = carreraOpt.get();
		carrera.setNombre(carreraDTO.getNombre());
		Carrera updatedCarrera = repositorio.save(carrera);
		return updatedCarrera;
	}

	public void eliminarCarrera(Long id) {
		Optional<Carrera> carreraOpt = obtenerCarrera(id);
		repositorio.delete(carreraOpt.get());
	}

}
