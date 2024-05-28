package com.teacherattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

	public List<Aula> findAll();
	
}
