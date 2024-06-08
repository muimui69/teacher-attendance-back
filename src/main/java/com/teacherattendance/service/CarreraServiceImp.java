package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.repository.CarreraRepository;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@Service
public class CarreraServiceImp {
	
	@Autowired
	private CarreraRepository repositorio;

	@Transactional(readOnly = true)
	public List<CarreraDTO> findAll() {
		List<Carrera> carreras = (List<Carrera>) repositorio.findAll();
		return carreras.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public CarreraDTO guardarCarrera(CarreraDTO carreraDTO) {
		Carrera carrera = new Carrera(carreraDTO.getId(), carreraDTO.getNombre());
		Carrera savedCarrera = repositorio.save(carrera);
		return convertToDto(savedCarrera);
	}

	public Optional<CarreraDTO> obtenerCarrera(Long id) {
		Optional<Carrera> carreraOpt = repositorio.findById(id);
		if (!carreraOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		return carreraOpt.map(this::convertToDto);
	}

	public CarreraDTO actualizarCarrera(Long id, CarreraDTO carreraDTO) {
		Optional<Carrera> carreraOpt = repositorio.findById(id);
		if (!carreraOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		carreraDTO.setId(null);
		Carrera carrera = carreraOpt.get();
		carrera.setNombre(carreraDTO.getNombre());
		Carrera updatedCarrera = repositorio.save(carrera);
		return convertToDto(updatedCarrera);
	}

	public void eliminarCarrera(Long id) {
		Optional<Carrera> carreraOpt = repositorio.findById(id);
		if (!carreraOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		repositorio.delete(carreraOpt.get());
	}

	private CarreraDTO convertToDto(Carrera carrera) {
		return new CarreraDTO(carrera.getId(), carrera.getNombre());
	}

}
