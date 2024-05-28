package com.teacherattendance.repository;

import com.teacherattendance.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<Usuarios, Long> {
	
	public Usuarios findByEmail(String email);
	
}
