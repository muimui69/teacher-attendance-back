package com.teacherattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.DetalleCargaHoraria;

@Repository
public interface DCargaHorariaRepository extends JpaRepository<DetalleCargaHoraria, Long> {
	
	public List<DetalleCargaHoraria> findAll();
	
}
