package com.teacherattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.dto.MateriaDTO;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.repository.MateriaRepository;

@Service
public class MateriaServiceImp {
	
	@Autowired
	private MateriaRepository repositorio;
	
	@Transactional(readOnly = true)
	public List<Materia> findAll(){
		return repositorio.findAll();
	}
	
	public Materia guardarMateria(MateriaDTO materiaDTO) {
		Materia materia = new Materia(materiaDTO.getId(), materiaDTO.getNombre(),
				materiaDTO.getSigla(), materiaDTO.getCarrera());  
		return repositorio.save(materia);
	}
	
	public Materia obtenerMateria(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));
	}
	
	public Materia actualizarMateria(Long id, MateriaDTO materiaDTO) {
		Materia materia = obtenerMateria(id);
		materia.setNombre(materiaDTO.getNombre());
		materia.setSigla(materiaDTO.getSigla());
		materia.setCarrera(materiaDTO.getCarrera());
		return repositorio.save(materia);
	}
	
	public void eliminarMateria(Long id) {
		repositorio.deleteById(id);
	}

}
