package com.teacherattendance.repository;

import java.util.List;
import java.util.Optional;

import com.teacherattendance.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long>{
	
	public List<Modulo> findAll();
	Optional<Modulo> findByNumero(int numero);

}
