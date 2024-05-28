package com.teacherattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>{

	public List<Materia> findAll();
	
}
