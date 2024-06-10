package com.teacherattendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
<<<<<<< Updated upstream
=======
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
>>>>>>> Stashed changes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModalidadDTO {
	
    private Long id;
	
	private String nombre;
	
	private String descripcion;


}
