package com.teacherattendance.service;

import com.teacherattendance.dto.DetalleCargaHorariaDTO;
import com.teacherattendance.entity.*;
import com.teacherattendance.repository.DetalleCargaHorariaRepository;
import com.teacherattendance.util.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DetalleCargaHorariaServiceImp {
    @Autowired
    private DetalleCargaHorariaRepository repositorio;

    @Autowired
    private AulaServiceImp aulaService;

    @Autowired
    private CargaHorariaServiceImp cargaHorariaService;
    
    @Autowired
    private GrupoServiceImp grupoService;

    @Autowired
    private DiasServiceImp diasService;

    @Transactional(readOnly = true)
    public List<DetalleCargaHoraria> findAll(){
        List<DetalleCargaHoraria> detallesCargaHorarias = repositorio.findAll();
        return detallesCargaHorarias;
    }

    @Transactional(readOnly = true)
    public List<DetalleCargaHoraria> detalleCargaHorariaPorDocente(Long idDocente) {
        List<DetalleCargaHoraria> todasLasCargas = findAll();
        return todasLasCargas.stream()
                .filter(detalle -> detalle.getCargaHoraria().getDocente().getId().equals(idDocente))
                .collect(Collectors.toList());
    }

    public DetalleCargaHoraria guardarDetalleCargaHoraria(DetalleCargaHorariaDTO detalleCargaHorariaDTO) {
        Optional<Aula> aulaOpt = aulaService.obtenerAula(detalleCargaHorariaDTO.getAulaId());
        Optional<CargaHoraria> cargaHorariaOpt = cargaHorariaService.obtenerCargaHoraria(detalleCargaHorariaDTO.getCargaHorariaId());
        Optional<Grupo> grupoOpt = grupoService.obtenerGrupo(detalleCargaHorariaDTO.getGrupoId());
        Optional<Dias> diasOpt = diasService.obtenerDia(detalleCargaHorariaDTO.getDiaId());

        List<DetalleCargaHoraria> detallesExistentes = repositorio.findByAulaIdAndDiasId(aulaOpt.get().getId(), diasOpt.get().getId());

        LocalTime horaInicioNueva = detalleCargaHorariaDTO.getHora_inicio();
        LocalTime horaFinNueva = detalleCargaHorariaDTO.getHora_fin();

        for (DetalleCargaHoraria detalleExistente : detallesExistentes) {
            LocalTime horaInicioExistente = detalleExistente.getHora_inicio();
            LocalTime horaFinExistente = detalleExistente.getHora_fin();
            Dias diaExistente = detalleExistente.getDias();

            if (diaExistente.getId().equals(diasOpt.get().getId())) {
                if (horaInicioNueva.isBefore(horaFinExistente) && horaFinNueva.isAfter(horaInicioExistente)) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,"El horario se superpone con un horario existente."
                    );
                }
            }
        }

        DetalleCargaHoraria detalleCargaHoraria = new DetalleCargaHoraria();
        detalleCargaHoraria.setHora_inicio(detalleCargaHorariaDTO.getHora_inicio());
        detalleCargaHoraria.setHora_fin(detalleCargaHorariaDTO.getHora_fin());
        detalleCargaHoraria.setAula(aulaOpt.get());
        detalleCargaHoraria.setCargaHoraria(cargaHorariaOpt.get());
        detalleCargaHoraria.setGrupo(grupoOpt.get());
        detalleCargaHoraria.setDias(diasOpt.get());
        return repositorio.save(detalleCargaHoraria);
    }
//    public DetalleCargaHoraria guardarDetalleCargaHoraria(DetalleCargaHorariaDTO detalleCargaHorariaDTO) {
//        Optional<Aula> aulaOpt = aulaService.obtenerAula(detalleCargaHorariaDTO.getAulaId());
//        Optional<CargaHoraria> cargaHorariaOpt = cargaHorariaService.obtenerCargaHoraria(detalleCargaHorariaDTO.getCargaHorariaId());
//        Optional<Grupo> grupoOpt = grupoService.obtenerGrupo(detalleCargaHorariaDTO.getGrupoId());
//        Optional<Dias> diasOpt = diasService.obtenerDia(detalleCargaHorariaDTO.getDiaId());
//
//        List<DetalleCargaHoraria> detallesExistentes = repositorio.findByAulaIdAndDiasId(aulaOpt.get().getId(), diasOpt.get().getId());
//
//        LocalTime horaInicioNueva = detalleCargaHorariaDTO.getHora_inicio();
//        LocalTime horaFinNueva = detalleCargaHorariaDTO.getHora_fin();
//        Long docenteId = cargaHorariaOpt.get().getDocente().getId(); // Obtener el ID del docente de la carga horaria nueva
//
//        for (DetalleCargaHoraria detalleExistente : detallesExistentes) {
//            LocalTime horaInicioExistente = detalleExistente.getHora_inicio();
//            LocalTime horaFinExistente = detalleExistente.getHora_fin();
//            Dias diaExistente = detalleExistente.getDias();
//            Long docenteExistenteId = detalleExistente.getCargaHoraria().getDocente().getId(); // Obtener el ID del docente del detalle existente
//
//            if (diaExistente.getId().equals(diasOpt.get().getId()) && docenteExistenteId.equals(docenteId)) {
//                if (horaInicioNueva.isBefore(horaFinExistente) && horaFinNueva.isAfter(horaInicioExistente)) {
//                    throw new ResponseStatusException(
//                            HttpStatus.BAD_REQUEST,"El horario se superpone con un horario existente para el mismo docente."
//                    );
//                }
//            }
//        }
//
//        DetalleCargaHoraria detalleCargaHoraria = new DetalleCargaHoraria();
//        detalleCargaHoraria.setHora_inicio(detalleCargaHorariaDTO.getHora_inicio());
//        detalleCargaHoraria.setHora_fin(detalleCargaHorariaDTO.getHora_fin());
//        detalleCargaHoraria.setAula(aulaOpt.get());
//        detalleCargaHoraria.setCargaHoraria(cargaHorariaOpt.get());
//        detalleCargaHoraria.setGrupo(grupoOpt.get());
//        detalleCargaHoraria.setDias(diasOpt.get());
//        return repositorio.save(detalleCargaHoraria);
//    }


    public Optional<DetalleCargaHoraria> obtenerDetalleCargaHoraria(Long id) {
        Optional<DetalleCargaHoraria> detalleCargaHorariaOpt = repositorio.findById(id);
        if (!detalleCargaHorariaOpt.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el detalle de carga horaria con el id " + id
            );
        }
        return detalleCargaHorariaOpt;
    }

    public DetalleCargaHoraria actualizarDetalleCargaHoraria(Long id, DetalleCargaHorariaDTO detalleCargaHorariaDTO){
        Optional<DetalleCargaHoraria> detalleCargaHorariaOpt = obtenerDetalleCargaHoraria(id);
        Optional<Aula> aulaOpt = aulaService.obtenerAula(detalleCargaHorariaDTO.getAulaId());
        Optional<CargaHoraria> cargaHorariaOpt = cargaHorariaService.obtenerCargaHoraria(detalleCargaHorariaDTO.getCargaHorariaId());
        Optional<Grupo> grupoOpt = grupoService.obtenerGrupo(detalleCargaHorariaDTO.getGrupoId());
        detalleCargaHorariaDTO.setId(null);
        DetalleCargaHoraria detalleCargaHoraria = detalleCargaHorariaOpt.get();
        detalleCargaHoraria.setHora_inicio(detalleCargaHorariaDTO.getHora_inicio());
        detalleCargaHoraria.setHora_fin(detalleCargaHorariaDTO.getHora_fin());
        detalleCargaHoraria.setAula(aulaOpt.get());
        detalleCargaHoraria.setCargaHoraria(cargaHorariaOpt.get());
        detalleCargaHoraria.setGrupo(grupoOpt.get());
        return repositorio.save(detalleCargaHoraria);
    }

    public void eliminarDetalleCargaHoraria(Long id) {
        Optional<DetalleCargaHoraria> detalleCargaHorariaOpt = obtenerDetalleCargaHoraria(id);
        repositorio.delete(detalleCargaHorariaOpt.get());
    }

}
