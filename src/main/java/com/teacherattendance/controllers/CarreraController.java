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
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.service.CarreraServiceImp;

@RestController
@CrossOrigin(origins = "")
public class CarreraController {
	
	@Autowired
	private CarreraServiceImp service;
	
	@GetMapping("/carrera")
	public List<Carrera> listarCarrera() {
		return service.findAll();
	}
	
	@PostMapping("/carrera")
	public Carrera guardarCarrera(@RequestBody Carrera carrera) {
		return service.guardarCarrera(carrera);
	}

	@GetMapping("/carrera/{id}")
	public ResponseEntity<Carrera> obtenerCarrera(@PathVariable Long id) {
		Carrera carrera =  service.obtenerCarrera(id);
		return ResponseEntity.ok(carrera);
	}

	@PutMapping("/carrera/{id}")
	public ResponseEntity<Carrera> actualizarCarrera(@PathVariable Long id,@RequestBody Carrera detalleCarrera) {
		Carrera carrera =  service.obtenerCarrera(id);
		carrera.setId(detalleCarrera.getId()); 
		carrera.setNombre(detalleCarrera.getNombre());
		Carrera carreraActualizado = service.actualizarCarrera(carrera);
		return ResponseEntity.ok(carreraActualizado);
	}

	@DeleteMapping("/carrera/{id}")
	public ResponseEntity<Map<String, Boolean>>  eliminarCarrera(@PathVariable Long id) {
		Carrera carrera =  service.obtenerCarrera(id);
		service.eliminarCarrera(carrera);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
