package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.teacherattendance.dto.GrupoDTO;
import com.teacherattendance.entity.Grupo;
import com.teacherattendance.repository.GrupoRepository;
import com.teacherattendance.util.HttpStatusMessage;

@Service
public class GrupoServiceImp {
	
	@Autowired
	private GrupoRepository repositorio;

	@Transactional(readOnly = true)
	public List<Grupo> findAll() {
		List<Grupo> grupo = repositorio.findAll();
		return grupo;
	}

	public Grupo guardarGrupo(GrupoDTO grupoDTO) {
		Grupo grupo = new Grupo();
		grupo.setId(grupoDTO.getId());
		grupo.setNombre(grupoDTO.getNombre());
		return repositorio.save(grupo);
	}

	public Optional<Grupo> obtenerGrupo(Long id) {
		Optional<Grupo> grupoOpt = repositorio.findById(id);
		if (!grupoOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,  "No existe el grupo con el id " + id
			);
		}
		return grupoOpt;
	}

	public Grupo actualizarGrupo(Long id, GrupoDTO grupoDTO) {
		Optional<Grupo> grupoOpt = obtenerGrupo(id);
		Grupo grupo = grupoOpt.get();
		grupo.setNombre(grupoDTO.getNombre());
		return repositorio.save(grupo);
	}

	public void eliminarGrupo(Long id) {
		Optional<Grupo> grupoOpt = obtenerGrupo(id);
		repositorio.delete(grupoOpt.get());
	}

}
