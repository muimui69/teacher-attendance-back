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
import com.teacherattendance.entity.Aula;
import com.teacherattendance.service.AulaServiceImp;

@RestController
@CrossOrigin(origins = "")
public class AulaController {
	
	@Autowired
	private AulaServiceImp service;
	
	@GetMapping("/aula")
	public List<Aula> listarAula() {
		return service.findAll();
	}
	
	@PostMapping("/aula")
	public Aula guardarAula(@RequestBody Aula aula) {
		return service.guardarAula(aula);
	}

	@GetMapping("/aula/{id}")
	public ResponseEntity<Aula> obtenerAula(@PathVariable Long id) {
		Aula aula =  service.obtenerAulaPorId(id);
		return ResponseEntity.ok(aula);
	}

	@PutMapping("/aula/{id}")
	public ResponseEntity<Aula> actualizarAula(@PathVariable Long id,@RequestBody Aula detalleAula) {
		Aula aula =  service.obtenerAulaPorId(id);
		aula.setId(detalleAula.getId()); 
		aula.setModulo(detalleAula.getModulo());
		aula.setNombre(detalleAula.getNombre());
		Aula aulaActualizado = service.actualizarAula(aula);
		return ResponseEntity.ok(aulaActualizado);
	}

	@DeleteMapping("/aula/{id}")
	public ResponseEntity<Map<String, Boolean>>  eliminarAula(@PathVariable Long id) {
		Aula aula =  service.obtenerAulaPorId(id);
		service.eliminarAula(aula);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
