package com.sura.bibloteca.service.impl;


import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.dto.RevistaDto;
import com.sura.bibloteca.dto.RevistaIdDto;
import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import com.sura.bibloteca.entity.RevistaEntity;
import com.sura.bibloteca.entity.RevistaIdEntity;
import com.sura.bibloteca.mapping.RevistaMapping;
import com.sura.bibloteca.repository.IelementoPrestableBaseRepository;
import com.sura.bibloteca.repository.IrevistaRepository;
import com.sura.bibloteca.service.IelementoPrestableBaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.NotContextException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RevistaImplTest {

    @Mock
    IrevistaRepository irevistaRepository;

    @Mock
    IelementoPrestableBaseService ielementoPrestableBaseService;

    @InjectMocks
    RevistaImpl revistaImpl;


    @Test
    void testCrearRevistaNull() {
        // Arrange
        RevistaIdDto revistaIdDto = RevistaIdDto.builder()
                .idElementoPrestableBase(1)
                .idRevista(1)
                .build();

        RevistaDto revistaDto = RevistaDto.builder()
                .Id(revistaIdDto)
                .build();

        when(ielementoPrestableBaseService.buscarElementoPrestableBase(anyInt())).thenReturn(null);

        // act
        NotContextException exception = assertThrows(NotContextException.class, () -> {
            revistaImpl.crearRevista(revistaDto);
        });

        // Assert
        assertEquals("Debe crear el elemento prestable base", exception.getMessage());
        verify(ielementoPrestableBaseService, times(1)).buscarElementoPrestableBase(anyInt());
        verifyNoMoreInteractions(irevistaRepository);
    }

    @Test
    void testCrearRevistaExcepcion() {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDto =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .titulo("Los indomables")
                        .build();

        RevistaIdDto revistaIdDto =
                RevistaIdDto.builder()
                        .idElementoPrestableBase(1)
                        .idRevista(1)
                        .build();

        RevistaDto revistaDto =
                RevistaDto.builder()
                        .Id(revistaIdDto)
                        .idElementoPrestableBase(elementoPrestableBaseDto)
                        .build();

        when(ielementoPrestableBaseService.buscarElementoPrestableBase(anyInt())).thenReturn(elementoPrestableBaseDto);
        when(irevistaRepository.saveAndFlush(any())).thenThrow(new RuntimeException("Error al guardar la revista"));

        // Act & Assert
        NotContextException exception = assertThrows(NotContextException.class, () -> {
            revistaImpl.crearRevista(revistaDto);
        });

        assertEquals("Error al guardar la revista", exception.getMessage());

        verify(ielementoPrestableBaseService, times(1)).buscarElementoPrestableBase(anyInt());
        verify(irevistaRepository, times(1)).saveAndFlush(any());
    }

    @Test
    void testCrearRevistaNotNull() throws NotContextException {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDto =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .tipoElementoPrestable("Libro")
                        .anoPublicacion("23/06/2022")
                        .genero("Terror")
                        .categoria("Adultos")
                        .inventario(2)
                        .titulo("Los indomables")
                        .build();

        ElementoPrestableBaseEntity elementoPrestableBaseEntity =
                ElementoPrestableBaseEntity.builder()
                        .idElementoPrestableBase(1)
                        .tipoElementoPrestable("Libro")
                        .anoPublicacion(LocalDate.of(2023, 5, 22))
                        .genero("Terror")
                        .categoria("Adultos")
                        .inventario(2)
                        .titulo("Los indomables")
                        .build();

        RevistaIdDto revistaIdDto =
                RevistaIdDto.builder()
                        .idElementoPrestableBase(1)
                        .idRevista(1)
                        .build();

        RevistaDto revistaDto =
                RevistaDto.builder()
                        .Id(revistaIdDto)
                        .numero(1)
                        .periocidad("g")
                        .idElementoPrestableBase(elementoPrestableBaseDto)
                        .build();

        RevistaIdEntity revistaIdEntity =
                RevistaIdEntity.builder()
                        .idElementoPrestableBase(1)
                        .idRevista(1)
                        .build();

        RevistaEntity revistaEntity =
                RevistaEntity.builder()
                        .idElementoPrestableBase(elementoPrestableBaseEntity)
                        .Id(revistaIdEntity)
                        .numero(2)
                        .periocidad("s")
                        .build();

        when(ielementoPrestableBaseService.buscarElementoPrestableBase(anyInt())).thenReturn(elementoPrestableBaseDto);
        when(irevistaRepository.saveAndFlush(any())).thenReturn(revistaEntity);

        // Act
        RevistaDto crearRevista = revistaImpl.crearRevista(revistaDto);

        // Assert
        assertNotNull(crearRevista);

        verify(ielementoPrestableBaseService, times(1)).buscarElementoPrestableBase(anyInt());
        verify(irevistaRepository, times(1)).saveAndFlush(any());
    }


    @Test
    void testBuscarRevista() {
        //arrange

        // act
        List<RevistaDto> buscarTodasRevistas = revistaImpl.buscarTodasRevistas();

        //Assert
        assertNull(buscarTodasRevistas);

    }

    @Test
    void testBuscarTodasRevistas() {
        //arrange
        RevistaIdDto revistaIdDtoDto =
                RevistaIdDto.builder()
                        .build();
        // act
        RevistaDto buscarRevista = revistaImpl.buscarRevista(revistaIdDtoDto);

        //Assert
        assertNull(buscarRevista);

    }

    @Test
    void testBorrarRevista() {
        //arrange
        RevistaDto revistaDto =
                RevistaDto.builder()
                        .build();
        // act
        String borrarRevista = revistaImpl.borrarRevista(revistaDto);

        //Assert
        assertNull(borrarRevista);

    }

    @Test
    void testActualizarRevista() {
        //arrange
        RevistaDto revistaDto =
                RevistaDto.builder()
                        .build();
        // act
        RevistaDto actualizarRevista = revistaImpl.actualizarRevista(revistaDto);

        //Assert
        assertNull(actualizarRevista);

    }
}
