package com.teacherattendance.service;

import com.teacherattendance.auth.AuthResponse;
import com.teacherattendance.auth.LoginRequest;
import com.teacherattendance.config.JwtService;
import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.entity.Roles;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.repository.RolRepository;
import com.teacherattendance.repository.UserRepository;

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

	@Override
	public List<Usuarios> listUser() {
		return userRepository.findAll();
	}

	@Override
	public Long getUserById(String name) {
		Usuarios usuario = userRepository.findByEmail(name)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	        return usuario.getId();
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
		Optional<Roles> optionalUserRole = rolRepository.findByNombre("ROLE_DOCENTE");
		Roles userRole = optionalUserRole.orElseGet(() -> rolRepository.save(new Roles ("ROLE_ADMIN")));
		Set<Roles> roles = Collections.singleton(userRole);
		Usuarios usuario = new Usuarios(userDto.getNombre(), 
				userDto.getApellido(), userDto.getEmail(), 
				passwordEncoder.encode(userDto.getPassword()), roles);
		userRepository.save(usuario);
		return AuthResponse.builder().token(jwtService.getToken(usuario)).build();
	}
	
	@Override
	public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user=userRepository.findByEmail(loginRequest.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
	}

	@Override
	public Usuarios patchAdmin(Long id, UserDTO adminDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
