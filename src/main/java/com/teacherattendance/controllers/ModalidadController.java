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
import com.teacherattendance.entity.Modalidad;
import com.teacherattendance.service.ModalidadServiceImp;

@RestController
@CrossOrigin(origins = "")
public class ModalidadController {
	
	@Autowired
	private ModalidadServiceImp service;
	
	@GetMapping("/modalidad")
	public List<Modalidad> listarModalidad() {
		return service.findAll();
	}
	
	@PostMapping("/modalidad")
	public Modalidad guardarModalidad(@RequestBody Modalidad modalidad) {
		return service.guardarModalidad(modalidad);
	}

	@GetMapping("/modalidad/{id}")
	public ResponseEntity<Modalidad> obtenerModalidad(@PathVariable Long id) {
		Modalidad modalidad =  service.obtenerModalidad(id);
		return ResponseEntity.ok(modalidad);
	}

	@PutMapping("/modalidad/{id}")
	public ResponseEntity<Modalidad> actualizarModalidad(@PathVariable Long id,@RequestBody Modalidad detalleModalidad) {
		Modalidad modalidad =  service.obtenerModalidad(id);
		modalidad.setId(detalleModalidad.getId()); 
		modalidad.setNombre(detalleModalidad.getNombre());
		modalidad.setDescripcion(detalleModalidad.getDescripcion());
		Modalidad modalidadActualizado = service.actualizarModalidad(modalidad);
		return ResponseEntity.ok(modalidadActualizado); 
	}

	@DeleteMapping("/modalidad/{id}")
	public ResponseEntity<Map<String, Boolean>>eliminarModalidad(@PathVariable Long id) {
		Modalidad modalidad =  service.obtenerModalidad(id);
		service.eliminarModalidad(modalidad);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}


}
