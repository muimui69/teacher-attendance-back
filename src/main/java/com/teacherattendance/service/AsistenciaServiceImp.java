package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.teacherattendance.dto.AsistenciaDTO;
import com.teacherattendance.entity.Asistencia;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.repository.AsistenciaRepository;
import com.teacherattendance.repository.UserRepository;
import com.teacherattendance.util.HttpStatusMessage;

@Service
public class AsistenciaServiceImp {
	
	@Autowired
	private AsistenciaRepository repositorio;
	
	@Autowired
	private UserRepository userRepositorio;
	
	@Transactional(readOnly = true)
	public List<Asistencia> findAll(){
		return (List<Asistencia>) repositorio.findAll();
	}
	
	public Asistencia guardarAsistencia(AsistenciaDTO asistenciaDto) {
		Usuarios docente = obtenerUsuarioAutenticado();
		Asistencia asistencia = new Asistencia(asistenciaDto.getId(), asistenciaDto.getEstado(), 
				asistenciaDto.getEntrada(), asistenciaDto.getSalida(), asistenciaDto.getToleranciaMinutos(), 
				asistenciaDto.getHorasAtraso(), docente);
		return repositorio.save(asistencia);
	}
	
	public Optional<Asistencia> obtenerAsistencia(Long id) {
		Optional<Asistencia> asistenciaOpt = repositorio.findById(id);
		if (!asistenciaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		return asistenciaOpt;
	}
	
	public Asistencia actualizarAsistencia(Long id, AsistenciaDTO asistenciaDTO){
		Optional<Asistencia> asistenciaOpt = repositorio.findById(id);
		if (!asistenciaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "No existe la materia con el id " + id
			);
		}
		Usuarios docente = obtenerUsuarioAutenticado();
		Asistencia asistencia = asistenciaOpt.get();
		asistencia.setEntrada(asistenciaDTO.getEntrada());
		asistencia.setSalida(asistenciaDTO.getSalida());
		asistencia.setEstado(asistenciaDTO.getEstado());
		asistencia.setHorasAtraso(asistenciaDTO.getHorasAtraso());
		asistencia.setToleranciaMinutos(asistenciaDTO.getToleranciaMinutos());
		asistencia.setDocente(docente);
		return repositorio.save(asistencia);
	}
	
	public void eliminarAsistencia(Long id) {
		Optional<Asistencia> asistenciaOpt = obtenerAsistencia(id);
		repositorio.delete(asistenciaOpt.get());
	}
	
    private Usuarios obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepositorio.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
