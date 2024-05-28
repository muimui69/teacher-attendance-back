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
import com.teacherattendance.entity.Modulo;
import com.teacherattendance.service.ModuloServiceImp;

@RestController
@CrossOrigin(origins = "")
public class ModuloController {
	
	@Autowired
	private ModuloServiceImp service;
	
	@GetMapping("/modulo")
	public List<Modulo> listarModulo() {
		return service.findAll();
	}
	
	@PostMapping("/modulo")
	public Modulo guardarModulo(@RequestBody Modulo modulo) {
		return service.guardarModulo(modulo);
	}

	@GetMapping("/modulo/{id}")
	public ResponseEntity<Modulo> obtenerModulo(@PathVariable Long id) {
		Modulo modulo =  service.obtenerModulo(id);
		return ResponseEntity.ok(modulo);
	}

	@PutMapping("/modulo/{id}")
	public ResponseEntity<Modulo> actualizarModulo(@PathVariable Long id,@RequestBody Modulo detalleModulo) {
		Modulo modulo =  service.obtenerModulo(id);
		modulo.setId(detalleModulo.getId()); 
		modulo.setNumero(detalleModulo.getNumero());
		modulo.setUbicacion(detalleModulo.getUbicacion());
		Modulo moduloActualizado = service.actualizarModulo(modulo);
		return ResponseEntity.ok(moduloActualizado);
	}

	@DeleteMapping("/modulo/{id}")
	public ResponseEntity<Map<String, Boolean>>  eliminarModulo(@PathVariable Long id) {
		Modulo modulo =  service.obtenerModulo(id);
		service.eliminarModulo(modulo);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}


}
