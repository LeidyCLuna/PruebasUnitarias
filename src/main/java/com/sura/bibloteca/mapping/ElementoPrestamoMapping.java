package com.sura.bibloteca.mapping;

import com.sura.bibloteca.dto.ElementoPrestamoDto;
import com.sura.bibloteca.entity.ElementoPrestamoEntity;
import com.sura.bibloteca.util.LocalDateFomatter;

public class ElementoPrestamoMapping {
    public ElementoPrestamoEntity elementoPrestamoDtoToElementoPrestamoEntity(ElementoPrestamoDto elementoPrestamoDto){
        return ElementoPrestamoEntity.builder()
                .id(elementoPrestamoDto.getIdPrestamo())
                .fechaPrestamo(new LocalDateFomatter().dateStringtoLocalDate(elementoPrestamoDto.getFechaPrestamo()))
                .fechaDevolucion(new LocalDateFomatter().dateStringtoLocalDate(elementoPrestamoDto.getFechaDevolucion()))
                .idEmpleado(new EmpleadoMapping().empleadoDtoToEmpleadoEntity(elementoPrestamoDto.getIdEmpleadoDto()))
                .idUsuario(new UsuarioMapping().usuarioDtoToUsuarioEntity(elementoPrestamoDto.getIdUsuarioDto()))
                .idElementoPrestableBase(new ElementoPrestableBaseMapping()
                                            .ElementoPrestableBaseDtoToElementoPrestableBaseEntity(elementoPrestamoDto
                                                    .getIdElementoPrestableBaseDTO()))
                .build();
    }

    public ElementoPrestamoDto elementoPrestamoEntityToElementoPrestamoDto(ElementoPrestamoEntity elementoPrestamo){
        return ElementoPrestamoDto.builder()
                .idPrestamo(elementoPrestamo.getId())
                .fechaPrestamo(new LocalDateFomatter().LocalDateToStringDate(elementoPrestamo.getFechaPrestamo()))
                .fechaDevolucion(new  LocalDateFomatter().LocalDateToStringDate(elementoPrestamo.getFechaDevolucion()))
                .idUsuarioDto(new UsuarioMapping().usuarioEntityToUsuarioDto(elementoPrestamo.getIdUsuario()))
                .idEmpleadoDto(new EmpleadoMapping().empleadoEntityToEmpleadoDto(elementoPrestamo.getIdEmpleado()))
                .idElementoPrestableBaseDTO(new ElementoPrestableBaseMapping()
                        .ElementoPrestableBaseEntityToElementoPrestableBaseDto(elementoPrestamo
                                .getIdElementoPrestableBase()))
                .build();
    }
}
