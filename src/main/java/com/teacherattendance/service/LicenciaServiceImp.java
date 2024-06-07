package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.LicenciaDTO;
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
	
	public Licencia guardarLicencia(LicenciaDTO licenciaDTO) {
		Licencia licencia = new Licencia(licenciaDTO.getId(), licenciaDTO.getTitulo(),
				licenciaDTO.getDescripcion(), licenciaDTO.getDocente(), licenciaDTO.getFecha());
		return repositorio.save(licencia);
	}
	
	public Licencia obtenerLicencia(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Licencia actualizarLicencia(Long id, LicenciaDTO licenciaDTO) {
		Licencia licencia = obtenerLicencia(id);
		licencia.setTitulo(licenciaDTO.getTitulo());
		licencia.setDescripcion(licenciaDTO.getDescripcion());
		licencia.setDocente(licenciaDTO.getDocente());
		licencia.setFecha(licenciaDTO.getFecha());
		return repositorio.save(licencia);
	}
	
	public void eliminarLicencia(Long id) {
		repositorio.deleteById(id);
	}

}
