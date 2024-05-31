package com.sura.bibloteca.service.impl;

import com.sura.bibloteca.dataprovider.client.IladiscordiaClient;
import com.sura.bibloteca.dataprovider.model.ListaPreguntasDto;
import com.sura.bibloteca.dataprovider.model.OpcionesPreguntaDto;
import com.sura.bibloteca.dataprovider.model.PreguntaDto;
import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import com.sura.bibloteca.repository.IelementoPrestableBaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElementoPrestableBaseServiceImplTest {
    @Mock
    IelementoPrestableBaseRepository ielementoPrestableBaseRepository;

    @Mock
    IladiscordiaClient iladiscordiaClient;

    @InjectMocks
    ElementoPrestableBaseServiceImpl elementoPrestableBaseService;


    @Test
    void testCrearElementoPrestableBaseTituloNull() {
        //arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
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

        when(ielementoPrestableBaseRepository.findByTitulo(any(String.class))).thenReturn(null);
        when(ielementoPrestableBaseRepository.saveAndFlush(any(ElementoPrestableBaseEntity.class))).thenReturn(elementoPrestableBaseEntity);

        //act
        ElementoPrestableBaseDTO crearElementoPrestableBase = elementoPrestableBaseService.crearElementoPrestableBase(elementoPrestableBaseDTO);

        //Assert
        assertNotNull(crearElementoPrestableBase);
        assertEquals("Los indomables", crearElementoPrestableBase.getTitulo());
        verify(iladiscordiaClient, times(1)).guardarPregunta(any());
        verify(ielementoPrestableBaseRepository, times(1)).saveAndFlush(any(ElementoPrestableBaseEntity.class));

    }


    @Test
    void testCrearElementoPrestableBaseTituloNotNull() {
        //arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
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

        when(ielementoPrestableBaseRepository.findByTitulo(any(String.class))).thenReturn(elementoPrestableBaseEntity);

        //act
        ElementoPrestableBaseDTO crearElementoPrestableBase = elementoPrestableBaseService.crearElementoPrestableBase(elementoPrestableBaseDTO);

        //Assert
        assertNull(crearElementoPrestableBase);
        verify(iladiscordiaClient, times(0)).guardarPregunta(any());
        verify(ielementoPrestableBaseRepository, times(0)).saveAndFlush(any(ElementoPrestableBaseEntity.class));

    }


    @Test
    void testCrearElementoPrestableBaseTituloNullNonFalla() {
        //arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
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

        when(ielementoPrestableBaseRepository.findByTitulo(any(String.class))).thenReturn(null);
        when(ielementoPrestableBaseRepository.saveAndFlush(any(ElementoPrestableBaseEntity.class))).thenReturn(null);

        //act
        ElementoPrestableBaseDTO crearElementoPrestableBase = elementoPrestableBaseService.crearElementoPrestableBase(elementoPrestableBaseDTO);

        //Assert
        assertNull(crearElementoPrestableBase);
        verify(iladiscordiaClient, times(1)).guardarPregunta(any());
        verify(ielementoPrestableBaseRepository, times(1)).saveAndFlush(any(ElementoPrestableBaseEntity.class));

    }


    @Test
    public void testCrearElementoPrestableBaseException() {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .titulo("Los indomables")
                        .build();

        when(ielementoPrestableBaseRepository.findByTitulo(anyString())).thenThrow(new RuntimeException("Error"));

        // Act y Assert
        assertThrows(RuntimeException.class, () -> {
            elementoPrestableBaseService.crearElementoPrestableBase(elementoPrestableBaseDTO);
        });

        verify(iladiscordiaClient, times(0)).guardarPregunta(any());
        verify(ielementoPrestableBaseRepository, times(0)).saveAndFlush(any(ElementoPrestableBaseEntity.class));
    }


    @Test
    public void testGuardarListaPreguntaNotNull() {
        // Act
        ListaPreguntasDto guardarListaPregunta = elementoPrestableBaseService.guardarListaPregunta();

        // Assert
        assertNotNull(guardarListaPregunta);
        assertNotNull(guardarListaPregunta.getPreguntas());
        assertFalse(guardarListaPregunta.getPreguntas().isEmpty());
    }


    @Test
    public void testGuardarListaPreguntaValores() {
        // Act
        ListaPreguntasDto guardarListaPregunta = elementoPrestableBaseService.guardarListaPregunta();

        // Assert
        List<PreguntaDto> preguntas = guardarListaPregunta.getPreguntas();
        assertEquals(1, preguntas.size());
        PreguntaDto pregunta = preguntas.get(0);
        assertEquals("Como vamos con la clase?", pregunta.getPregunta());
        assertEquals("Abierta", pregunta.getTipoPregunta());

        List<OpcionesPreguntaDto> opciones = pregunta.getOpcionesPreguntaDtos();
        assertNotNull(opciones);
        assertEquals(1, opciones.size());
        OpcionesPreguntaDto opcion = opciones.get(0);
        assertEquals("R", opcion.getId());
        assertEquals("", opcion.getValue());
    }


    @Test
    public void testGuardarListaPreguntaFecha() {
        // Act
        ListaPreguntasDto guardarListaPregunta = elementoPrestableBaseService.guardarListaPregunta();

        // Assert
        assertEquals("01/04/2024", guardarListaPregunta.getFechaRegistro());
    }


    @Test
    void testActualizarElementoPrestableBaseNotNull() {
        //arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
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

        when(ielementoPrestableBaseRepository.findById(anyInt())).thenReturn(Optional.ofNullable(elementoPrestableBaseEntity));
        when(ielementoPrestableBaseRepository.saveAndFlush(any(ElementoPrestableBaseEntity.class))).thenReturn(elementoPrestableBaseEntity);

        //act
        ElementoPrestableBaseDTO actualizarElementoPrestableBase = elementoPrestableBaseService.actualizarElementoPrestableBase(elementoPrestableBaseDTO);

        //Assert
        assertEquals("Los indomables", actualizarElementoPrestableBase.getTitulo());
        assertNotNull(actualizarElementoPrestableBase);
        verify(ielementoPrestableBaseRepository, times(1)).findById(anyInt());
        verify(ielementoPrestableBaseRepository, times(1)).saveAndFlush(any(ElementoPrestableBaseEntity.class));
    }


    @Test
    void testActualizarElementoPrestableBaseNull() {
        //arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
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
        when(ielementoPrestableBaseRepository.findById(anyInt())).thenReturn(Optional.ofNullable(elementoPrestableBaseEntity));

        //act
        ElementoPrestableBaseDTO actualizarElementoPrestableBase = elementoPrestableBaseService.actualizarElementoPrestableBase(elementoPrestableBaseDTO);

        //Assert

        assertNull(actualizarElementoPrestableBase);
        verify(ielementoPrestableBaseRepository, times(1)).findById(anyInt());
        verify(ielementoPrestableBaseRepository, times(1)).saveAndFlush(any(ElementoPrestableBaseEntity.class));
    }


    @Test
    public void testActualizarElementoPrestableBaseErrorBuscar() {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .build();

        when(ielementoPrestableBaseRepository.findById(anyInt())).thenThrow(new RuntimeException("Error de búsqueda"));

        // Act y Assert
        assertThrows(RuntimeException.class, () -> {
            elementoPrestableBaseService.actualizarElementoPrestableBase(elementoPrestableBaseDTO);
        });

        verify(ielementoPrestableBaseRepository, times(1)).findById(anyInt());
        verify(ielementoPrestableBaseRepository, times(0)).saveAndFlush(any(ElementoPrestableBaseEntity.class));
    }


    @Test
    public void testActualizarElementoPrestableBaseErrorAct() {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .build();

        ElementoPrestableBaseEntity elementoPrestableBaseEntity =
                ElementoPrestableBaseEntity.builder()
                        .idElementoPrestableBase(1)
                        .build();

        when(ielementoPrestableBaseRepository.findById(anyInt())).thenReturn(Optional.of(elementoPrestableBaseEntity));
        when(ielementoPrestableBaseRepository.saveAndFlush(any(ElementoPrestableBaseEntity.class))).thenThrow(new RuntimeException("Error de actualización"));

        // Act y Assert
        assertThrows(RuntimeException.class, () -> {
            elementoPrestableBaseService.actualizarElementoPrestableBase(elementoPrestableBaseDTO);
        });

        verify(ielementoPrestableBaseRepository, times(1)).findById(anyInt());
        verify(ielementoPrestableBaseRepository, times(1)).saveAndFlush(any(ElementoPrestableBaseEntity.class));
    }



    @Test
    void testBuscarTodosElementosPestablesBaseNull() {
        //arrange
        List<ElementoPrestableBaseEntity> elementoPrestableBaseEntity = List.of();
        when(ielementoPrestableBaseRepository.findAll()).thenReturn(elementoPrestableBaseEntity);
        //act
        List<ElementoPrestableBaseDTO> buscarTodosElementosPestablesBase = elementoPrestableBaseService.buscarTodosElementosPestablesBase();
        //Assert
        assertNull(buscarTodosElementosPestablesBase);

    }


    @Test
    void testBuscarTodosElementosPestablesBaseNotNull() {
        //arrange
        List<ElementoPrestableBaseEntity> elementoPrestableBaseEntity = Arrays.asList(
                ElementoPrestableBaseEntity.builder()
                        .idElementoPrestableBase(1)
                        .tipoElementoPrestable("Libro")
                        .anoPublicacion(LocalDate.of(2023, 5, 22))
                        .genero("Terror")
                        .categoria("Adultos")
                        .inventario(2)
                        .titulo("Los indomables")
                        .build()
        );
        when(ielementoPrestableBaseRepository.findAll()).thenReturn(elementoPrestableBaseEntity);
        //act
        List<ElementoPrestableBaseDTO> buscarTodosElementosPestablesBase = elementoPrestableBaseService.buscarTodosElementosPestablesBase();

        //Assert
        assertNotNull(buscarTodosElementosPestablesBase);
        assertEquals(1, buscarTodosElementosPestablesBase.size());

    }


    @Test
    void testBuscarElementoPestablesBaseNull() {
        //arrange
        when(ielementoPrestableBaseRepository.findById(anyInt())).thenReturn(Optional.empty());
        //act
        ElementoPrestableBaseDTO buscarElementoPrestableBase = elementoPrestableBaseService.buscarElementoPrestableBase(anyInt());

        //Assert
        assertNull(buscarElementoPrestableBase);
        verify(ielementoPrestableBaseRepository, times(1)).findById(anyInt());

    }


    @Test
    void testBuscarElementoPestablesBaseNotNull() {
        //arrange
        Optional<ElementoPrestableBaseEntity> elementoPrestableBaseEntity = Optional.of(
                ElementoPrestableBaseEntity.builder()
                        .idElementoPrestableBase(1)
                        .titulo("Los indomables")
                        .build()
        );

        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .titulo("Los indomables")
                        .build();

        when(ielementoPrestableBaseRepository.findById(anyInt())).thenReturn(elementoPrestableBaseEntity);
        //act
        ElementoPrestableBaseDTO buscarElementoPrestableBase = elementoPrestableBaseService.buscarElementoPrestableBase(anyInt());

        //Assert
        assertNotNull(buscarElementoPrestableBase);
        assertEquals(elementoPrestableBaseDTO.getIdElementoPrestableBase(), buscarElementoPrestableBase.getIdElementoPrestableBase());
        assertEquals(elementoPrestableBaseDTO.getTitulo(), buscarElementoPrestableBase.getTitulo());
    }


    @Test
    public void testBuscarElementoPrestableBaseException() {
        // Arrange

        when(ielementoPrestableBaseRepository.findById(anyInt())).thenThrow(new RuntimeException("Error de búsqueda"));

        // Act y Assert
        assertThrows(RuntimeException.class, () -> {
            elementoPrestableBaseService.buscarElementoPrestableBase(anyInt());
        });

        verify(ielementoPrestableBaseRepository, times(1)).findById(anyInt());
    }


    @Test
    void testBorrarElementoPrestableBaseNull() {
        //arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .build();
        when(ielementoPrestableBaseRepository.findById(anyInt())).thenReturn(Optional.empty());

        // act
        String borrarElementoPrestableBase = elementoPrestableBaseService.borrarElementoPrestableBase(elementoPrestableBaseDTO);

        //Assert
        assertNull(borrarElementoPrestableBase);

    }


    @Test
    void testBorrarElementoPrestableBaseNotNull() {
        //arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .build();

        ElementoPrestableBaseEntity elementoPrestableBaseEntity =
                ElementoPrestableBaseEntity.builder()
                        .idElementoPrestableBase(1)
                        .build();
        when(ielementoPrestableBaseRepository.findById(anyInt())).thenReturn(Optional.ofNullable(elementoPrestableBaseEntity));

        // act
        String borrarElementoPrestableBase = elementoPrestableBaseService.borrarElementoPrestableBase(elementoPrestableBaseDTO);

        //Assert
        assertNotNull(borrarElementoPrestableBase);
        assertEquals("EL elemento fue borrado exitosamente", borrarElementoPrestableBase);
        verify(ielementoPrestableBaseRepository, times(1)).delete(any(ElementoPrestableBaseEntity.class));

    }


    @Test
    void testBorrarElementoPrestableBaseExcepcion() {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .idElementoPrestableBase(1)
                        .build();

        when(elementoPrestableBaseService.buscarElementoPrestableBase(1)).thenThrow(new RuntimeException("Error en la búsqueda"));

        // Act y Assert
        assertThrows(RuntimeException.class, () -> {
            elementoPrestableBaseService.borrarElementoPrestableBase(elementoPrestableBaseDTO);
        });
        verify(ielementoPrestableBaseRepository, never()).delete(any());
    }

}
