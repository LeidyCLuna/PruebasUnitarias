package com.sura.bibloteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.dto.RevistaDto;
import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import com.sura.bibloteca.service.IelementoPrestableBaseService;
import com.sura.bibloteca.service.IrevistaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ElementoPrestableBaseController.class)
public class ElementoPrestableBaseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    IelementoPrestableBaseService ielementoPrestableBaseService;


    @Test
    public void testGuardarElementoPrestableBase() throws Exception {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .build();

        ElementoPrestableBaseDTO elementoPrestableCreadoBaseDTO =
                ElementoPrestableBaseDTO.builder()
                        .build();

        when(ielementoPrestableBaseService.crearElementoPrestableBase(any(ElementoPrestableBaseDTO.class))).thenReturn(elementoPrestableCreadoBaseDTO);

        // Act y Assert
        mockMvc.perform(post("/biblioteca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(elementoPrestableBaseDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(elementoPrestableCreadoBaseDTO)));
    }

    @Test
    public void testActualizarElementoPrestableBase() throws Exception {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO = new ElementoPrestableBaseDTO();

        ElementoPrestableBaseDTO elementoPrestableBaseResponse = new ElementoPrestableBaseDTO();

        when(ielementoPrestableBaseService.actualizarElementoPrestableBase(any(ElementoPrestableBaseDTO.class)))
                .thenReturn(elementoPrestableBaseResponse);

        // Act y Assert
        mockMvc.perform(put("/biblioteca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(elementoPrestableBaseDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(elementoPrestableBaseResponse)));
    }

    @Test
    public void testBuscarElementoPrestableBase() throws Exception {
        // Arrange
        int idElementoPrestableBaseDTO = 1;
        ElementoPrestableBaseDTO elementoPrestableBaseResponse = new ElementoPrestableBaseDTO();

        when(ielementoPrestableBaseService.buscarElementoPrestableBase(anyInt()))
                .thenReturn(elementoPrestableBaseResponse);

        // Act y Assert
        mockMvc.perform(get("/biblioteca")
                        .param("idElementoPrestableBaseDTO", String.valueOf(idElementoPrestableBaseDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(elementoPrestableBaseResponse)));
    }

    @Test
    public void testBorrarElementoPrestableBase() throws Exception {
        // Arrange
        ElementoPrestableBaseDTO elementoPrestableBaseDTO = new ElementoPrestableBaseDTO();

        String mensajeRespuesta = "Elemento eliminado correctamente";
        when(ielementoPrestableBaseService.borrarElementoPrestableBase(any(ElementoPrestableBaseDTO.class)))
                .thenReturn(mensajeRespuesta);

        // Act y Assert
        mockMvc.perform(delete("/biblioteca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(elementoPrestableBaseDTO)))
                .andExpect(status().isOk());
    }
}
