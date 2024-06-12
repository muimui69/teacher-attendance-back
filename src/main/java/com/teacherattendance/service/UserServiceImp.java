package com.teacherattendance.service;

import com.teacherattendance.auth.AuthResponse;
import com.teacherattendance.auth.LoginRequest;
import com.teacherattendance.config.JwtService;
import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.dto.error.ResourceNotFoundException;
import com.teacherattendance.entity.Roles;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.repository.RolRepository;
import com.teacherattendance.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service 
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;
	
	@Autowired 
	private ModelMapper modelMapper;

	@Override
	public List<UserDTO> listUser() {
		List<Usuarios> user = userRepository.findAll();
        return user.stream()
                .map(usuario -> modelMapper.map(usuario, UserDTO.class))
                .collect(Collectors.toList());
	}
	
	@Override
	public List<Usuarios> listUsuarios() {
		return userRepository.findAll();
	}

	@Override
	public Long getUserById(String name) {
		Usuarios usuario = userRepository.findByEmail(name)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	        return usuario.getId();
	}
	
	@Override
	public Usuarios obtenerUserPorId(Long id) {
		Optional<Usuarios> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}else {
			throw new ResourceNotFoundException("El usuario no se encuentra");
		}
	}

	@Override
	public AuthResponse createUser(UserDTO userDto) {
		Optional<Roles> optionalUserRole = rolRepository.findByNombre("ROLE_DOCENTE");
		Roles userRole = optionalUserRole.orElseGet(() -> rolRepository.save(new Roles ("ROLE_DOCENTE")));
		Set<Roles> roles = Collections.singleton(userRole);
		Usuarios usuario = new Usuarios(userDto.getNombre(), 
				userDto.getApellido(), userDto.getEmail(), 
				passwordEncoder.encode(userDto.getPassword()), roles);
		userRepository.save(usuario);
		return AuthResponse.builder().token(jwtService.getToken(usuario)).build();
	}
	
	@Override
	public AuthResponse createUserAdmin(UserDTO userDto) {
		Optional<Roles> optionalUserRole = rolRepository.findByNombre("ROLE_ADMIN");
		Roles userRole = optionalUserRole.orElseGet(() -> rolRepository.save(new Roles ("ROLE_ADMIN")));
		Set<Roles> roles = Collections.singleton(userRole);
		Usuarios usuario = new Usuarios(userDto.getNombre(), 
				userDto.getApellido(), userDto.getEmail(), 
				passwordEncoder.encode(userDto.getPassword()), roles);
		userRepository.save(usuario);
		return AuthResponse.builder().token(jwtService.getToken(usuario)).build();
	}

	public Usuarios getUser(String email) {
		Usuarios usuario = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		return usuario;
	}


	@Override
	public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user=userRepository.findByEmail(loginRequest.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
		Usuarios userDetails = this.getUser(user.getUsername());
        return AuthResponse.builder()
            .token(token)
			.email(userDetails.getEmail())
				.role(userDetails.getRoles().iterator().next())
				.nombre(userDetails.getNombre())
				.apellido(userDetails.getApellido())
				.id(userDetails.getId())
            .build();
	}

	@Override
	public Usuarios updateUser(Long id, UserDTO userDto) {
		Usuarios user = obtenerUserPorId(id);
		user.setNombre(userDto.getNombre());
		user.setApellido(userDto.getApellido());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
