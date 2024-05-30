package com.teacherattendance.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.dto.MateriaDTO;
import com.teacherattendance.entity.Materia;
import com.teacherattendance.service.MateriaServiceImp;

@RestController
@CrossOrigin(origins = "")
public class MateriaController {

	@Autowired
	private MateriaServiceImp service;
	
	@GetMapping("/materia")
	public List<Materia> listarMateria() {
		return service.findAll();
	}
	
	@PostMapping("/materia")
	public Materia guardarMateria(@RequestBody MateriaDTO materiaDTO) {
		return service.guardarMateria(materiaDTO);
	}

	@GetMapping("/materia/{id}")
	public ResponseEntity<Materia> obtenerMateria(@PathVariable Long id) {
		Materia materia =  service.obtenerMateria(id);
		return ResponseEntity.ok(materia);
	}

	@PutMapping("/materia/{id}")
	public ResponseEntity<Materia> actualizarMateria(@PathVariable Long id,@RequestBody Materia detalleMateria) {
		Materia materia =  service.obtenerMateria(id);
		materia.setId(detalleMateria.getId()); 
		materia.setCarrera(detalleMateria.getCarrera());
		materia.setNombre(detalleMateria.getNombre());
		materia.setSigla(detalleMateria.getSigla());
		Materia materiaActualizado =  service.actualizarMateria(materia);
		return ResponseEntity.ok(materiaActualizado);
	}

	@DeleteMapping("/materia/{id}")
	public ResponseEntity<Map<String, Boolean>>  eliminarMateria(@PathVariable Long id) {
		Materia materia =  service.obtenerMateria(id);
		service.eliminarMateria(materia);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
	
}
