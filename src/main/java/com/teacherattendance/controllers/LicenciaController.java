package com.teacherattendance.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherattendance.dto.LicenciaDTO;
import com.teacherattendance.entity.Licencia;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.service.LicenciaServiceImp;
import com.teacherattendance.service.UserServiceImp;

@RestController
@CrossOrigin(origins = "")
@RequestMapping("/api/")
public class LicenciaController {
	
	@Autowired
	private LicenciaServiceImp service;
	
	@Autowired
	private UserServiceImp userService;
	
	@GetMapping("/licencia")
	public List<Licencia> listarAula() {
		return service.findAll();
	}
	
	@PostMapping("/licencia")
	public Licencia guardarLicencia(@Validated @RequestBody LicenciaDTO licenciaDto) throws Exception{
		List<Usuarios> user = userService.listUser(); 
		return service.guardarLicencia(licenciaDto); 
	}

	@GetMapping("/licencia/{id}")
	public ResponseEntity<Licencia> obtenerLicencia(@PathVariable Long id) {
		Licencia licencia =  service.obtenerLicencia(id);
		return ResponseEntity.ok(licencia);
	}

	@PutMapping("/aula/{id}")
	public ResponseEntity<Licencia> actualizarLicencia(@PathVariable Long id,@RequestBody Licencia detalleLicencia) {
		Licencia licencia =  service.obtenerLicencia(id);
		licencia.setId(detalleLicencia.getId()); 
		licencia.setTitulo(detalleLicencia.getTitulo());
		licencia.setDescripcion(detalleLicencia.getDescripcion());
		licencia.setDocente(detalleLicencia.getDocente());
		licencia.setFecha(detalleLicencia.getFecha());
		Licencia Actualizado = service.actualizarLicencia(licencia);
		return ResponseEntity.ok(Actualizado);
	}

	@DeleteMapping("/licencia/{id}")
	public ResponseEntity<Map<String, Boolean>>  eliminarLicencia(@PathVariable Long id) {
		Licencia licencia =  service.obtenerLicencia(id);
		service.eliminarLicencia(licencia);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
