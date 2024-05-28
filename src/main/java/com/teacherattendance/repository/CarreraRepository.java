package com.teacherattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
	
	public List<Carrera> findAll();
	
}
