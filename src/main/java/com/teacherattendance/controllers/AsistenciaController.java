package com.teacherattendance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.dto.AsistenciaDTO;
import com.teacherattendance.entity.Asistencia;
import com.teacherattendance.service.AsistenciaServiceImp;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin(origins = "http://localhost:4200")
public class AsistenciaController {
	
	@Autowired
	private AsistenciaServiceImp service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<AsistenciaDTO>> listarAsistencia() {
		List<Asistencia> asistencias = service.findAll();
		List<AsistenciaDTO> asistenciaDTO = asistencias.stream()
				.map(asistencia -> modelMapper.map(asistencia, AsistenciaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(asistenciaDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public Asistencia guardarAsistencia(@RequestBody AsistenciaDTO asistencia) {
		return service.guardarAsistencia(asistencia);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AsistenciaDTO> obtenerAsistencia(@PathVariable Long id) {
		Asistencia asistencia =  service.obtenerAsistencia(id);
		AsistenciaDTO asistenciaDTO = modelMapper.map(asistencia, AsistenciaDTO.class);
		return  new ResponseEntity<>(asistenciaDTO, HttpStatus.OK);
	}

//	@PutMapping("/asistencia/{id}")
//	public ResponseEntity<Asistencia> actualizarAsistencia(@PathVariable Long id,@RequestBody Asistencia detalleAsistencia) {
//		Asistencia asistencia =  service.obtenerAsistencia(id);
//		asistencia.setId(detalleAsistencia.getId());
//		asistencia.setDocente(detalleAsistencia.getDocente());
//		asistencia.setEntrada(detalleAsistencia.getEntrada());
//		asistencia.setEstado(detalleAsistencia.getEstado());
//		asistencia.setHorasAtraso(detalleAsistencia.getHorasAtraso());
//		asistencia.setSalida(detalleAsistencia.getSalida());
//		asistencia.setToleranciaMinutos(detalleAsistencia.getToleranciaMinutos());
//		Asistencia asistenciaActualizado = service.actualizarAsistencia(asistencia);
//		return ResponseEntity.ok(asistenciaActualizado);
//	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<Map<String, Boolean>>  eliminarAsistencia(@PathVariable Long id) {
//		Asistencia asistencia =  service.obtenerAsistencia(id);
//		service.eliminarAsistencia(asistencia);
//		Map<String, Boolean> respuesta = new HashMap<>();
//		respuesta.put("eliminar", Boolean.TRUE);
//		return ResponseEntity.ok(respuesta);
//	}

}
