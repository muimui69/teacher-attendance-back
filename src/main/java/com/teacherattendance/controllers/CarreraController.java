package com.teacherattendance.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.util.HttpStatusMessage;
import com.teacherattendance.util.ModelMapperTransform;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.teacherattendance.dto.CarreraDTO;
import com.teacherattendance.entity.Carrera;
import com.teacherattendance.service.CarreraServiceImp;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
	
	@Autowired
	private CarreraServiceImp service;

	@GetMapping
	public ResponseEntity<ApiResponse<List<CarreraDTO>>> listarCarreras() {
		List<CarreraDTO> carreras = service.findAll();
		ApiResponse<List<CarreraDTO>> response = ApiResponse.<List<CarreraDTO>>builder()
				.statusCode(HttpStatus.OK.value())
				.message(HttpStatusMessage.getMessage(HttpStatus.OK))
				.data(carreras)
				.build();
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ApiResponse<CarreraDTO> crearCarrera(@Valid @RequestBody CarreraDTO carreraDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return ApiResponse.<CarreraDTO>builder()
					.errors(errors)
					.build();
		}
		CarreraDTO carreraCreada = service.guardarCarrera(carreraDTO);
		return ApiResponse.<CarreraDTO>builder()
				.statusCode(HttpStatus.OK.value())
				.message(HttpStatusMessage.getMessage(HttpStatus.OK))
				.data(carreraCreada)
				.build();
	}

	@GetMapping("/{id}")
	public ApiResponse<CarreraDTO> obtenerCarrera(@Valid @PathVariable Long id) {
		Optional<CarreraDTO> carreraOpt = service.obtenerCarrera(id);
		if (!carreraOpt.isPresent()) {
			return ApiResponse.<CarreraDTO>builder()
					.statusCode(HttpStatus.NOT_FOUND.value())
					.message(HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND))
					.build();
		}

		return ApiResponse.<CarreraDTO>builder()
				.statusCode(HttpStatus.OK.value())
				.message(HttpStatusMessage.getMessage(HttpStatus.OK))
				.data(carreraOpt.get())
				.build();
	}

//	@PatchMapping("/{id}")
//	public ApiResponse<Carrera> actualizarCarrera(@PathVariable Long id, @Valid @RequestBody CarreraDTO carreraDTO, BindingResult bindingResult) {
//		Optional<Carrera> carreraOpt = service.obtenerCarrera(id);
//		if (!carreraOpt.isPresent()) {
//			return ApiResponse.<Carrera>builder()
//					.statusCode(HttpStatus.NOT_FOUND.value())
//					.message(HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND))
//					.build();
//		}
//
//		if (bindingResult.hasErrors()) {
//			List<String> errors = bindingResult.getAllErrors().stream()
//					.map(DefaultMessageSourceResolvable::getDefaultMessage)
//					.collect(Collectors.toList());
//			return ApiResponse.<Carrera>builder()
//					.errors(errors)
//					.build();
//		}
//
//		carreraDTO.setId(null);
//		Carrera carrera = carreraOpt.get();
//
//		ModelMapperTransform.map(carreraDTO, carrera);
//
//		Carrera carreraActualizada = service.actualizarCarrera(carrera);
//		return ApiResponse.<Carrera>builder()
//				.statusCode(HttpStatus.OK.value())
//				.message(HttpStatusMessage.getMessage(HttpStatus.OK))
//				.data(carreraActualizada)
//				.build();
//	}



//	@DeleteMapping("/{id}")
//	public ApiResponse<Carrera>  eliminarCarrera(@PathVariable Long id) {
//		Optional<Carrera> carreraOpt =  service.obtenerCarrera(id);
//		if (!carreraOpt.isPresent()) {
//			return ApiResponse.<Carrera>builder()
//					.statusCode(HttpStatus.NOT_FOUND.value())
//					.message(HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND))
//					.build();
//		}
//		Carrera carrera = carreraOpt.get();
//		service.eliminarCarrera(carrera);
//		return ApiResponse.<Carrera>builder()
//				.statusCode(HttpStatus.OK.value())
//				.message(HttpStatusMessage.getMessage(HttpStatus.OK))
//				.data(carreraActualizada)
//				.build();


//		Carrera carrera = carreraOpt.get();
//		Carrera carreraEliminada = service.eliminarCarrera(carrera);
//		return ApiResponse.<Carrera>builder()
//				.statusCode(HttpStatus.OK.value())
//				.message(HttpStatusMessage.getMessage(HttpStatus.OK))
//				.data(carreraEliminada)
//				.build();
//	}

}
