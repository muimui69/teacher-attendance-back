package com.teacherattendance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
=======
import org.springframework.http.HttpStatus;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
import com.teacherattendance.util.HttpStatusMessage;
>>>>>>> Stashed changes

@Service
public class LicenciaServiceImp {
	
	@Autowired
	private LicenciaRepository repositorio;
	
	@Autowired
<<<<<<< Updated upstream
	private UserRepository userRepositorio;
=======
	private UserRepository userRepository;
>>>>>>> Stashed changes
	
	@Transactional(readOnly = true)
	public List<Licencia> findAll(){
		return (List<Licencia>) repositorio.findAll();
	}
	
	public Licencia guardarLicencia(LicenciaDTO licenciaDTO) {
		Usuarios docente = obtenerUsuarioAutenticado();
		Licencia licencia = new Licencia(licenciaDTO.getId(), licenciaDTO.getTitulo(),
<<<<<<< Updated upstream
				licenciaDTO.getDescripcion(), obtenerUsuarioAutenticado(), licenciaDTO.getFecha());
=======
				licenciaDTO.getDescripcion(), docente, licenciaDTO.getFecha());
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		Licencia licencia = obtenerLicencia(id);
		licencia.setTitulo(licenciaDTO.getTitulo());
		licencia.setDescripcion(licenciaDTO.getDescripcion());
		licencia.setDocente(licenciaDTO.getDocente());
		licencia.setFecha(licenciaDTO.getFecha());
=======
		Optional<Licencia> licenciaOpt = obtenerLicencia(id);
		Usuarios docente = obtenerUsuarioAutenticado();
		licenciaDTO.setId(null);
		Licencia licencia = licenciaOpt.get();
		licencia.setTitulo(licenciaDTO.getTitulo());
		licencia.setDescripcion(licenciaDTO.getDescripcion());
		licencia.setFecha(licenciaDTO.getFecha());
		licencia.setDocente(docente);
>>>>>>> Stashed changes
		return repositorio.save(licencia);
	}
	
	public void eliminarLicencia(Long id) {
<<<<<<< Updated upstream
		repositorio.deleteById(id);
=======
		Optional<Licencia> licenciaOpt = obtenerLicencia(id);
		repositorio.delete(licenciaOpt.get());
>>>>>>> Stashed changes
	}
	
    private Usuarios obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
<<<<<<< Updated upstream
        return userRepositorio.findByEmail(username)
=======
        return userRepository.findByEmail(username)
>>>>>>> Stashed changes
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
