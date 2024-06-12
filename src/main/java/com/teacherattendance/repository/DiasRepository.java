package com.teacherattendance.repository;

import com.teacherattendance.entity.Dias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiasRepository  extends JpaRepository<Dias, Long> {
    public List<Dias> findAll();

}
