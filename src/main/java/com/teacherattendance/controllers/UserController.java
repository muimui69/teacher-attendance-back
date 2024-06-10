package com.teacherattendance.controllers;

import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.service.UserService;
import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< Updated upstream
=======
import org.springframework.validation.BindingResult;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
<<<<<<< Updated upstream
@RequestMapping("/api/user")
=======
@RequestMapping("/user")
>>>>>>> Stashed changes
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
    private UserService service;
	
	@Autowired
    private ModelMapper modelMapper;

<<<<<<< Updated upstream
    @GetMapping
    public ResponseEntity<List<Usuarios>> getAllAdmins() {
        return ResponseEntity.ok(service.listUsuarios());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
//        Usuarios user = service.obtenerUserPorId(id);
//        UserDTO userDto = modelMapper.map(user, UserDTO.class);
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getUserById(@PathVariable("id") Long id) {
        Usuarios user = service.obtenerUserPorId(id);
//        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDTO userDto) {
        Usuarios patchAdmin = service.updateAdmin(id, userDto);
        UserDTO patchAdminDto = modelMapper.map(patchAdmin, UserDTO.class);
        return new ResponseEntity<>(patchAdminDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
=======
	@Autowired
    private UserService service;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Usuarios>>> listarUsuarios() {
		List<Usuarios> user = service.listUser();
		return new ResponseEntity<>(
				ApiResponse.<List<Usuarios>>builder()
						.statusCode(HttpStatus.OK.value())
						.message(HttpStatusMessage.getMessage(HttpStatus.OK))
						.data(user)
						.build(),
				HttpStatus.OK
		);
	}

    
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Usuarios>> obtenerUsuario(@PathVariable Long id) {
		try {
			Usuarios usuariosOpt = service.obtenerUserPorId(id);
			return new ResponseEntity<>(
					ApiResponse.<Usuarios>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(usuariosOpt)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Usuarios>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}
    

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Usuarios>>actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(
					ApiResponse.<Usuarios>builder()
							.errors(errors)
							.build(),
					HttpStatus.BAD_REQUEST
			);
		}

		try {
			Usuarios usuarioActualizado = service.updateUser(id, userDTO);
			return new ResponseEntity<>(
					ApiResponse.<Usuarios>builder()
							.statusCode(HttpStatus.OK.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.OK))
							.data(usuarioActualizado)
							.build(),
					HttpStatus.OK
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Usuarios>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminarUsuario(@PathVariable Long id) {
		try {
			service.deleteUser(id);
			return new ResponseEntity<>(
					ApiResponse.<Void>builder()
							.statusCode(HttpStatus.NO_CONTENT.value())
							.message(HttpStatusMessage.getMessage(HttpStatus.NO_CONTENT))
							.build(),
					HttpStatus.NO_CONTENT
			);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(
					ApiResponse.<Void>builder()
							.statusCode(e.getStatusCode().value())
							.message(e.getReason())
							.build(),
					e.getStatusCode()
			);
		}
	}
>>>>>>> Stashed changes
}
