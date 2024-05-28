package com.teacherattendance.service;

import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.entity.Roles;
import com.teacherattendance.entity.Usuarios;
import com.teacherattendance.repository.RolRepository;
import com.teacherattendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service 
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	private RolRepository rolRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuario = userRepository.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Roles> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).
				collect(Collectors.toSet());
	}

	@Override
	public List<Usuarios> listUser() {
		return userRepository.findAll();
	}

	@Override
	public Long getUserById(String name) {
		Usuarios usuario = userRepository.findByEmail(name);
		return (usuario != null) ? usuario.getId() : null;
	}

	@Override
	public Usuarios createUser(UserDTO userDto) {
		Optional<Roles> optionalUserRole = rolRepository.findByNombre("ROLE_DOCENTE");
		Roles userRole = optionalUserRole.orElseGet(() -> rolRepository.save(new Roles ("ROLE_DOCENTE")));
		Set<Roles> roles = Collections.singleton(userRole);
		Usuarios usuario = new Usuarios(userDto.getNombre(), 
				userDto.getApellido(), userDto.getEmail(), 
				passwordEncoder.encode(userDto.getPassword()), roles);
		return userRepository.save(usuario);
	}

	@Override
	public Usuarios patchAdmin(Long id, UserDTO adminDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
