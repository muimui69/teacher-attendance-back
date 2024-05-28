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
import com.teacherattendance.entity.Asistencia;
import com.teacherattendance.service.AsistenciaServiceImp;

@RestController
@CrossOrigin(origins = "")
public class AsistenciaController {
	
	@Autowired
	private AsistenciaServiceImp service;
	
	@GetMapping("/asistencia")
	public List<Asistencia> listarAsistencia() {
		return service.findAll();
	}
	
	@PostMapping("/asistencia")
	public Asistencia guardarAsistencia(@RequestBody Asistencia asistencia) {
		return service.guardarAsistencia(asistencia);
	}

	@GetMapping("/asistencia/{id}")
	public ResponseEntity<Asistencia> obtenerAsistencia(@PathVariable Long id) {
		Asistencia asistencia =  service.obtenerAsistencia(id);
		return ResponseEntity.ok(asistencia);
	}

	@PutMapping("/asistencia/{id}")
	public ResponseEntity<Asistencia> actualizarAsistencia(@PathVariable Long id,@RequestBody Asistencia detalleAsistencia) {
		Asistencia asistencia =  service.obtenerAsistencia(id);
		asistencia.setId(detalleAsistencia.getId());
		asistencia.setDocente(detalleAsistencia.getDocente());
		asistencia.setEntrada(detalleAsistencia.getEntrada());
		asistencia.setEstado(detalleAsistencia.getEstado());
		asistencia.setHorasAtraso(detalleAsistencia.getHorasAtraso());
		asistencia.setSalida(detalleAsistencia.getSalida());
		asistencia.setToleranciaMinutos(detalleAsistencia.getToleranciaMinutos());
		Asistencia asistenciaActualizado = service.actualizarAsistencia(asistencia);
		return ResponseEntity.ok(asistenciaActualizado);
	}

	@DeleteMapping("/asistencia/{id}")
	public ResponseEntity<Map<String, Boolean>>  eliminarAsistencia(@PathVariable Long id) {
		Asistencia asistencia =  service.obtenerAsistencia(id);
		service.eliminarAsistencia(asistencia);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
