package com.teacherattendance.repository;

import com.teacherattendance.entity.Usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Usuarios, Long> {
	
	public Optional<Usuarios> findByEmail(String email);
	
}
