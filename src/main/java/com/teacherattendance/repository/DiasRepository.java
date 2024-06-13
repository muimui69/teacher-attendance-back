package com.teacherattendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teacherattendance.entity.Dias;

@Repository
public interface DiasRepository  extends JpaRepository<Dias, Long> {
    public List<Dias> findAll();

}