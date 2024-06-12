package com.teacherattendance.service;

import com.teacherattendance.dto.DiasDTO;
import com.teacherattendance.dto.ModuloDTO;
import com.teacherattendance.entity.Dias;
import com.teacherattendance.entity.Modulo;
import com.teacherattendance.repository.DiasRepository;
import com.teacherattendance.repository.ModuloRepository;
import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DiasServiceImp {
    @Autowired
    private DiasRepository repositorio;

    @Transactional(readOnly = true)
    public List<Dias> findAll(){
        List<Dias> dias = repositorio.findAll();
        return dias;
    }

    public Dias guardarDia(DiasDTO diasDTO) {
        Dias dias = new Dias();
        dias.setNombre(diasDTO.getNombre());
        return repositorio.save(dias);
    }

    public Optional<Dias> obtenerDia(Long id) {
        Optional<Dias> diasOpt = repositorio.findById(id);
        if (!diasOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, HttpStatusMessage.getMessage(HttpStatus.NOT_FOUND)
            );
        }
        return diasOpt;
    }


}
