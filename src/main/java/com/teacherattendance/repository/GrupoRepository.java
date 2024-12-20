package com.teacherattendance.repository;

import java.util.List;
import java.util.Optional;

import com.teacherattendance.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{
	
	public List<Grupo> findAll();
	Optional<Grupo> findByNombre(String nombre);

}
