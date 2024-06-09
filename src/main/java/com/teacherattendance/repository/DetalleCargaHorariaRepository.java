package com.teacherattendance.repository;

import com.teacherattendance.entity.DetalleCargaHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCargaHorariaRepository  extends JpaRepository<DetalleCargaHoraria, Long> {
    public List<DetalleCargaHoraria> findAll();
}
