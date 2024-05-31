package com.sura.bibloteca.mapping;

import com.sura.bibloteca.dto.EmpleadoDto;
import com.sura.bibloteca.entity.EmpleadoEntity;

public class EmpleadoMapping {

    public EmpleadoEntity empleadoDtoToEmpleadoEntity(EmpleadoDto empleadoDto){
        return EmpleadoEntity.builder()
                .id(empleadoDto.getIdEmpleado())
                .nombre(empleadoDto.getNombre())
                .build();
    }

    public EmpleadoDto empleadoEntityToEmpleadoDto(EmpleadoEntity empleadoEntity){
        return EmpleadoDto.builder()
                .idEmpleado(empleadoEntity.getId())
                .nombre(empleadoEntity.getNombre())
                .build();
    }
}
