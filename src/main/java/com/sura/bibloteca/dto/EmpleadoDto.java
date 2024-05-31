package com.sura.bibloteca.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDto {

    @JsonProperty("id_empleado")
    private Integer idEmpleado;

    private String nombre;
}
