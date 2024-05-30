package com.teacherattendance.service;

import com.teacherattendance.auth.AuthResponse;
import com.teacherattendance.auth.LoginRequest;
import com.teacherattendance.dto.UserDTO;
import com.teacherattendance.entity.Usuarios;

import java.util.List;

public interface UserService {

	public List<Usuarios> listUser();

    public Long getUserById(String name);

    public AuthResponse createUser(UserDTO adminDto);
    
    public AuthResponse createUserAdmin(UserDTO adminDto);

    public Usuarios patchAdmin(Long id, UserDTO adminDto);
    
    public AuthResponse login(LoginRequest loginRequest);

//    void deleteAdmin(Long id);
}
