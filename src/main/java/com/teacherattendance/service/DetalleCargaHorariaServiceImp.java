package com.teacherattendance.service;

import com.teacherattendance.dto.DetalleCargaHorariaDTO;
import com.teacherattendance.entity.*;
import com.teacherattendance.repository.AulaRepository;
import com.teacherattendance.repository.CargaHorariaRepository;
import com.teacherattendance.repository.DetalleCargaHorariaRepository;
import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class DetalleCargaHorariaServiceImp {
    @Autowired
    private DetalleCargaHorariaRepository repositorio;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private CargaHorariaRepository cargaHorariaRepository;

    @Transactional(readOnly = true)
    public List<DetalleCargaHoraria> findAll(){
        List<DetalleCargaHoraria> detallesCargaHorarias = repositorio.findAll();
        return detallesCargaHorarias;
    }

    public DetalleCargaHoraria guardarDetalleCargaHoraria(DetalleCargaHorariaDTO detalleCargaHorariaDTO) {
        Optional<Aula> aulaOpt = aulaRepository.findById(detalleCargaHorariaDTO.getAulaId());
        if (!aulaOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el aula con el id " + detalleCargaHorariaDTO.getAulaId()
            );
        }

        Optional<CargaHoraria> cargaHorariaOpt = cargaHorariaRepository.findById(detalleCargaHorariaDTO.getCargaHorariaId());
        if (!cargaHorariaOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe la carga horaria con el id " + detalleCargaHorariaDTO.getCargaHorariaId()
            );
        }

        DetalleCargaHoraria detalleCargaHoraria = new DetalleCargaHoraria();
        detalleCargaHoraria.setHora_inicio(detalleCargaHorariaDTO.getHora_inicio());
        detalleCargaHoraria.setHora_fin(detalleCargaHorariaDTO.getHora_fin());
        detalleCargaHoraria.setAula(aulaOpt.get());
        detalleCargaHoraria.setCargaHoraria(cargaHorariaOpt.get());
        return repositorio.save(detalleCargaHoraria);
    }



    public Optional<DetalleCargaHoraria> obtenerDetalleCargaHoraria(Long id) {
        Optional<DetalleCargaHoraria> detalleCargaHorariaOpt = repositorio.findById(id);
        if (!detalleCargaHorariaOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
            );
        }
        return detalleCargaHorariaOpt;
    }

    public DetalleCargaHoraria actualizarDetalleCargaHoraria(Long id, DetalleCargaHorariaDTO detalleCargaHorariaDTO){
        Optional<DetalleCargaHoraria> detalleCargaHorariaOpt = repositorio.findById(id);
        if (!detalleCargaHorariaOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el detalle carga horaria con el id " + detalleCargaHorariaDTO.getId()
            );
        }

        Optional<Aula> aulaOpt = aulaRepository.findById(detalleCargaHorariaDTO.getAulaId());
        if (!aulaOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el aula con el id " + detalleCargaHorariaDTO.getAulaId()
            );
        }

        Optional<CargaHoraria> cargaHorariaOpt = cargaHorariaRepository.findById(detalleCargaHorariaDTO.getCargaHorariaId());
        if (!cargaHorariaOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe la carga horaria con el id " + detalleCargaHorariaDTO.getCargaHorariaId()
            );
        }

        detalleCargaHorariaDTO.setId(null);
        DetalleCargaHoraria detalleCargaHoraria = detalleCargaHorariaOpt.get();
        detalleCargaHoraria.setHora_inicio(detalleCargaHorariaDTO.getHora_inicio());
        detalleCargaHoraria.setHora_fin(detalleCargaHorariaDTO.getHora_fin());
        detalleCargaHoraria.setAula(aulaOpt.get());
        detalleCargaHoraria.setCargaHoraria(cargaHorariaOpt.get());
        return repositorio.save(detalleCargaHoraria);
    }


    public void eliminarDetalleCargaHoraria(Long id) {
        Optional<DetalleCargaHoraria> detalleCargaHorariaOpt = repositorio.findById(id);
        if (!detalleCargaHorariaOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
            );
        }
        repositorio.delete(detalleCargaHorariaOpt.get());
    }

}
