package com.teacherattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Licencia;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long>{
	
	public List<Licencia> findAll();
	
}
