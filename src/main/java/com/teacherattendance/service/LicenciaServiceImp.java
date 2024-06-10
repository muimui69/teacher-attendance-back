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
import com.teacherattendance.dto.LicenciaDTO;
import com.teacherattendance.entity.Licencia;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.repository.LicenciaRepository;
import com.teacherattendance.repository.UserRepository;
import com.teacherattendance.util.HttpStatusMessage;

@Service
public class LicenciaServiceImp {
	
	@Autowired
	private LicenciaRepository repositorio;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<Licencia> findAll(){
		return (List<Licencia>) repositorio.findAll();
	}
	
	public Licencia guardarLicencia(LicenciaDTO licenciaDTO) {
		Usuarios docente = obtenerUsuarioAutenticado();
		Licencia licencia = new Licencia(licenciaDTO.getId(), licenciaDTO.getTitulo(),
				licenciaDTO.getDescripcion(), docente, licenciaDTO.getFecha());
		return repositorio.save(licencia);
	}
	
	public Optional<Licencia> obtenerLicencia(Long id) {
		Optional<Licencia> licenciaOpt = repositorio.findById(id);
		if (!licenciaOpt.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
			);
		}
		return licenciaOpt;
	}
	
	public Licencia actualizarLicencia(Long id, LicenciaDTO licenciaDTO) {
		Optional<Licencia> licenciaOpt = obtenerLicencia(id);
		Usuarios docente = obtenerUsuarioAutenticado();
		licenciaDTO.setId(null);
		Licencia licencia = licenciaOpt.get();
		licencia.setTitulo(licenciaDTO.getTitulo());
		licencia.setDescripcion(licenciaDTO.getDescripcion());
		licencia.setFecha(licenciaDTO.getFecha());
		licencia.setDocente(docente);
		return repositorio.save(licencia);
	}
	
	public void eliminarLicencia(Long id) {
		Optional<Licencia> licenciaOpt = obtenerLicencia(id);
		repositorio.delete(licenciaOpt.get());
	}
	
    private Usuarios obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
