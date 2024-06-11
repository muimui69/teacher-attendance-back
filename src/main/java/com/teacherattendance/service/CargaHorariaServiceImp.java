package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.teacherattendance.dto.CargaHorariaDTO;
import com.teacherattendance.entity.CargaHoraria;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.entity.Modalidad;
import com.teacherattendance.entity.Periodo;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.repository.CargaHorariaRepository;
import com.teacherattendance.util.HttpStatusMessage;

@Service
public class CargaHorariaServiceImp {

	@Autowired
	private CargaHorariaRepository repositorio;
	
	@Autowired
	private UserServiceImp userService;
	
	@Autowired
	private MateriaServiceImp materiaService;
	
	@Autowired
	private PeriodoServiceImp periodoService;
	
	@Autowired
	private ModalidadServiceImp modalidadService;
	
	@Transactional(readOnly = true)
	public List<CargaHoraria> findAll() {
		return (List<CargaHoraria>) repositorio.findAll();
	}

	public CargaHoraria guardarCargaHoraria(CargaHorariaDTO cargaHorariaDTO) {
		Usuarios user = userService.obtenerUserPorId(cargaHorariaDTO.getId_docente());
		Optional<Materia> materia = materiaService.obtenerMateria(cargaHorariaDTO.getId_materia());
		Optional<Periodo> periodo = periodoService.obtenerPeriodo(cargaHorariaDTO.getId_periodo());
		Optional<Modalidad> modalidad = modalidadService.obtenerModalidad(cargaHorariaDTO.getId_modalidad());
		CargaHoraria cargaHoraria = new CargaHoraria();
		cargaHoraria.setDocente(user);
		cargaHoraria.setMateria(materia.get());
		cargaHoraria.setPeriodo(periodo.get());
		cargaHoraria.setModalidad(modalidad.get());
		return repositorio.save(cargaHoraria);
	}

	public Optional<CargaHoraria> obtenerCargaHoraria(Long id) {
		Optional<CargaHoraria> cargaHoraria = repositorio.findById(id);
		if (!cargaHoraria.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		return cargaHoraria;
	}

	public CargaHoraria actualizarCargaHoraria(Long id, CargaHorariaDTO cargaHorariaDTO) {
		Usuarios user = userService.obtenerUserPorId(cargaHorariaDTO.getId_docente());
		Optional<Materia> materia = materiaService.obtenerMateria(cargaHorariaDTO.getId_materia());
		Optional<Periodo> periodo = periodoService.obtenerPeriodo(cargaHorariaDTO.getId_periodo());
		Optional<Modalidad> modalidad = modalidadService.obtenerModalidad(cargaHorariaDTO.getId_modalidad());
		Optional<CargaHoraria> cargaHorariaOpt = obtenerCargaHoraria(id);
		CargaHoraria cargaHoraria = cargaHorariaOpt.get();
		cargaHoraria.setDocente(user);
		cargaHoraria.setMateria(materia.get());
		cargaHoraria.setPeriodo(periodo.get());
		cargaHoraria.setModalidad(modalidad.get());
		return repositorio.save(cargaHoraria);
	}

	public void eliminarCargaHoraria(Long id) {
		Optional<CargaHoraria> cargaHorariaOpt = obtenerCargaHoraria(id);
		repositorio.delete(cargaHorariaOpt.get());

	}

	
}
