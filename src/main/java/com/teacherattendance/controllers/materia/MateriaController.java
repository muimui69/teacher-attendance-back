package com.teacherattendance.controllers.materia;

import com.teacherattendance.reponse.ApiResponse;
import com.teacherattendance.dto.materia.MateriaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private List<MateriaDTO> materias = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ApiResponse<List<MateriaDTO>>> getAllMaterias() {
        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.OK.value(), "Estas son todas las materias.", materias),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MateriaDTO>> getMateriaById(@PathVariable Long id) {
        for (MateriaDTO materia : materias) {
            if (materia.getId().equals(id)) {
                return new ResponseEntity<>(
                        new ApiResponse<>(HttpStatus.OK.value(), "Materia retrieved successfully", materia),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Materia not found",null),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> createMateria(@RequestBody MateriaDTO materiaDTO) {
        materias.add(materiaDTO);
        return new ResponseEntity<>(materiaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
        for (MateriaDTO materia : materias) {
            if (materia.getId().equals(id)) {
                materias.remove(materia);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MateriaDTO> patchMateria(@PathVariable Long id, @RequestBody MateriaDTO patchMateria) {
        for (MateriaDTO materia : materias) {
            if (materia.getId().equals(id)) {
                if (patchMateria.getNombre() != null) {
                    materia.setNombre(patchMateria.getNombre());
                }
                if (patchMateria.getDescripcion() != null) {
                    materia.setDescripcion(patchMateria.getDescripcion());
                }
                return new ResponseEntity<>(materia, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
