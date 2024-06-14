package com.teacherattendance.repository;

import com.teacherattendance.entity.Carrera;
import com.teacherattendance.entity.Dias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiasRepository  extends JpaRepository<Dias, Long> {
    public List<Dias> findAll();
    Optional<Dias> findByNombre(String nombre);

}
