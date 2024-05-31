package com.sura.bibloteca.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import com.sura.bibloteca.entity.EmpleadoEntity;
import com.sura.bibloteca.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ElementoPrestamoDto {

    @JsonProperty("id_prestamo")
    private Integer idPrestamo;

    @JsonProperty("elemento_prestable_base")
    private ElementoPrestableBaseDTO idElementoPrestableBaseDTO;

    @JsonProperty("usuario")
    private UsuarioDto idUsuarioDto;

    @JsonProperty("empleado")
    private EmpleadoDto idEmpleadoDto;

    @JsonProperty("fecha_prestamo")
    private String fechaPrestamo;

    @JsonProperty("fecha_devolucion")
    private String fechaDevolucion;

}
