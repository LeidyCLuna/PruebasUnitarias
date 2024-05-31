package com.sura.bibloteca.service.impl;

import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.dto.ElementoPrestamoDto;
import com.sura.bibloteca.dto.EmpleadoDto;
import com.sura.bibloteca.dto.UsuarioDto;
import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import com.sura.bibloteca.entity.ElementoPrestamoEntity;
import com.sura.bibloteca.entity.EmpleadoEntity;
import com.sura.bibloteca.entity.UsuarioEntity;
import com.sura.bibloteca.repository.IelementoPrestamoRepository;
import com.sura.bibloteca.repository.IusuarioRepository;
import com.sura.bibloteca.service.IelementoPrestableBaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElementoPrestamoImplTest {
    @Mock
    IelementoPrestamoRepository ielementoPrestamoRepository;

    @Mock
    IusuarioRepository iusuarioRepository;

    @Mock
    IelementoPrestableBaseService ielementoPrestableBaseService;

    @InjectMocks
    ElementoPrestamoImpl elementoPrestamoImpl;


    @Test
    void testCrearElementoPrestamoUsuarioNull() {
        //arrange

        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .titulo("Los indomables")
                        .build();

        ElementoPrestamoDto elementoPrestamoDTO =
                ElementoPrestamoDto.builder()
                        .idElementoPrestableBaseDTO(elementoPrestableBaseDTO)
                        .idPrestamo(1)
                        .build();


        ElementoPrestamoEntity elementoPrestamoEntity =
                ElementoPrestamoEntity.builder()
                        .id(1)
                        .build();
        // act

        ElementoPrestamoDto resultado = elementoPrestamoImpl.crearElementoPrestamo(elementoPrestamoDTO);

        //Assert
        verify(ielementoPrestableBaseService, times(1)).buscarElementoPrestableBase(anyInt());
        assertNull(resultado);

    }

    @Test
    void testCrearElementoPrestamoUsuarioNotNull() {
        //arrange

        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .titulo("Los indomables")
                        .inventario(2)
                        .build();

        ElementoPrestableBaseEntity elementoPrestableBaseEntity =
                ElementoPrestableBaseEntity.builder()
                        .idElementoPrestableBase(1)
                        .titulo("Los indomables")
                        .inventario(2)
                        .build();

        UsuarioDto usuarioDto =
                UsuarioDto.builder()
                        .idUsuario(1)
                        .limitePrestamos(4)
                        .build();

        EmpleadoDto empleadoDto =
                EmpleadoDto.builder()
                        .idEmpleado(1)
                        .nombre("Pepito")
                        .build();

        EmpleadoEntity empleadoEntity =
                EmpleadoEntity.builder()
                        .id(1)
                        .nombre("Pepito")
                        .build();

        UsuarioEntity usuarioEntity =
                UsuarioEntity.builder()
                        .id(1)
                        .limitePrestamos(4)
                        .build();

        ElementoPrestamoDto elementoPrestamoDTO =
                ElementoPrestamoDto.builder()
                        .idUsuarioDto(usuarioDto)
                        .idElementoPrestableBaseDTO(elementoPrestableBaseDTO)
                        .idEmpleadoDto(empleadoDto)
                        .idPrestamo(1)
                        .build();


        ElementoPrestamoEntity elementoPrestamoEntity =
                ElementoPrestamoEntity.builder()
                        .id(1)
                        .idUsuario(usuarioEntity)
                        .idEmpleado(empleadoEntity)
                        .idElementoPrestableBase(elementoPrestableBaseEntity)
                        .build();

        when(ielementoPrestableBaseService.buscarElementoPrestableBase(anyInt())).thenReturn(elementoPrestableBaseDTO);
        when(iusuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuarioEntity));
        when(ielementoPrestamoRepository.saveAndFlush(any(ElementoPrestamoEntity.class))).thenReturn(elementoPrestamoEntity);

        // act

        ElementoPrestamoDto resultado = elementoPrestamoImpl.crearElementoPrestamo(elementoPrestamoDTO);

        //Assert
        verify(ielementoPrestableBaseService, times(1)).actualizarElementoPrestableBase(any());
        assertNotNull(resultado);

    }


    @Test
    void testActualizarElementoPrestamo() {
        //arrange
        ElementoPrestamoDto elementoPrestamoDto =
                ElementoPrestamoDto.builder()
                        .build();
        // act
        ElementoPrestamoDto actualizarElementoPrestamo = elementoPrestamoImpl.actualizarElementoPrestamo(elementoPrestamoDto);

        //Assert
        assertNull(actualizarElementoPrestamo);

    }

    @Test
    void testBuscarElementoPrestamo() {
        //arrange
        int id = 1;

        // act
        ElementoPrestamoDto buscarElementoPrestamo = elementoPrestamoImpl.buscarElementoPrestamo(id);

        //Assert
        assertNull(buscarElementoPrestamo);

    }

    @Test
    void testBorrarElementoPrestamo() {
        //arrange
        ElementoPrestamoDto elementoPrestamoDto =
                ElementoPrestamoDto.builder()
                        .build();
        // act
        String borrarElementoPrestamo = elementoPrestamoImpl.borrarElementoPrestamo(elementoPrestamoDto);

        //Assert
        assertNull(borrarElementoPrestamo);
    }

    @Test
    void testBuscarTodosElementosPrestamos() {
        //arrange

        // act
        List<ElementoPrestamoDto> buscarElementoPrestamo = elementoPrestamoImpl.buscarTodosElementoPrestamoDtos();

        //Assert
        assertNull(buscarElementoPrestamo);

    }

    @Test
    public void testRestarInventario() throws Exception {
        //arrange
        Method metodoPrivado = ElementoPrestamoImpl.class.getDeclaredMethod("restarInventario", int.class);
        metodoPrivado.setAccessible(true);

        //act
        int inventarioInicial = 5;
        Object resultado = metodoPrivado.invoke(elementoPrestamoImpl, inventarioInicial);

        //Assert
        assertEquals(inventarioInicial - 1, resultado);
    }

    @Test
    public void testSumarInventario() throws Exception {
        //arrange
        Method metodoPrivado = ElementoPrestamoImpl.class.getDeclaredMethod("sumarInventario", int.class);
        metodoPrivado.setAccessible(true);

        //act
        int inventarioInicial = 5;
        Object resultado = metodoPrivado.invoke(elementoPrestamoImpl, inventarioInicial);

        //Assert
        assertEquals(inventarioInicial + 1, resultado);
    }

}
