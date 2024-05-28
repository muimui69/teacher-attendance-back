package com.teacherattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Periodo;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Long>{

	public List<Periodo> findAll();
	
}
