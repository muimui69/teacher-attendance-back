package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.repository.CarreraRepository;

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
		Optional<Carrera> carrera = repositorio.findById(id);
		return carrera.map(this::convertToDto);
	}

//	public Carrera actualizarCarrera(Carrera carrera) {
//		return repositorio.save(carrera);
//	}
	
//	public void eliminarCarrera(Carrera carrera) {
//		repositorio.delete(carrera);
//	}

	private CarreraDTO convertToDto(Carrera carrera) {
		return new CarreraDTO(carrera.getId(), carrera.getNombre());
	}

	private Carrera convertToEntity(CarreraDTO carreraDTO) {
		return new Carrera(carreraDTO.getId(), carreraDTO.getNombre());
	}

}
