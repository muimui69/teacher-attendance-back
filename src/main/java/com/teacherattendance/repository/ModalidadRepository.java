package com.teacherattendance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Modalidad;

@Repository
public interface ModalidadRepository extends JpaRepository<Modalidad, Long>{
	
	public List<Modalidad> findAll();
	Optional<Modalidad> findByNombre(String nombre);
}
