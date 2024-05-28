package com.teacherattendance.service;

import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.entity.Usuarios;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

	public List<Usuarios> listUser();

    Long getUserById(String name);

    Usuarios createUser(UserDTO adminDto);

    Usuarios patchAdmin(Long id, UserDTO adminDto);

//    void deleteAdmin(Long id);
}
