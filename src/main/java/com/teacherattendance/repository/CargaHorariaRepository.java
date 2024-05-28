package com.teacherattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.CargaHoraria;

@Repository
public interface CargaHorariaRepository extends JpaRepository<CargaHoraria, Long> {
	
	public List<CargaHoraria> findAll();
	
}
