package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Licencia;
import com.teacherattendance.repository.LicenciaRepository;

@Service
public class LicenciaServiceImp {
	
	@Autowired
	private LicenciaRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Licencia> findAll(){
		return (List<Licencia>) repositorio.findAll();
	}
	
	public Licencia guardarLicencia(Licencia licencia) {
		return repositorio.save(licencia);
	}
	
	public Licencia obtenerLicencia(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Licencia actualizarLicencia(Licencia licencia) {
		return repositorio.save(licencia);
	}
	
	public void eliminarLicencia(Licencia licencia) {
		repositorio.delete(licencia);
	}

}
