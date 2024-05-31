package com.sura.bibloteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.dto.ElementoPrestamoDto;
import com.sura.bibloteca.service.IelementoPrestableBaseService;
import com.sura.bibloteca.service.IelementoPrestamoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ElementoPrestamoController.class)
public class ElementoPrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    IelementoPrestamoService ielementoPrestamoService;


    @Test
    public void testGuardarElementoPrestamo() throws Exception {
        // Arrange
        ElementoPrestamoDto elementoPrestamoDto =
                ElementoPrestamoDto.builder()
                        .build();

        ElementoPrestamoDto elementoPrestamoCreadoDto =
                ElementoPrestamoDto.builder()
                        .build();

        when(ielementoPrestamoService.crearElementoPrestamo(any(ElementoPrestamoDto.class))).thenReturn(elementoPrestamoCreadoDto);

        // Act y Assert
        mockMvc.perform(post("/biblioteca/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(elementoPrestamoDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(elementoPrestamoCreadoDto)));
    }
}
