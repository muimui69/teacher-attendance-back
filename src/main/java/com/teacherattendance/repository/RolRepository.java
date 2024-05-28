package com.teacherattendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Roles;

@Repository
public interface RolRepository extends JpaRepository<Roles, Long> {
	
	Optional<Roles> findByNombre(String nombre);

}
