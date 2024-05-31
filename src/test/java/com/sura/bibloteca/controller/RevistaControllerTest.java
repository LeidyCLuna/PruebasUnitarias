package com.sura.bibloteca.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sura.bibloteca.service.IrevistaService;
import com.sura.bibloteca.dto.RevistaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(RevistaController.class)
public class RevistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    IrevistaService irevistaService;


    @Test
    public void testGuardarRevista() throws Exception {
        // Arrange
        RevistaDto revistaDto =
                RevistaDto.builder()
                        .build();

        RevistaDto revistaCreada =
                RevistaDto.builder()
                        .build();

        when(irevistaService.crearRevista(any(RevistaDto.class))).thenReturn(revistaCreada);

        // Act y Assert
        mockMvc.perform(post("/biblioteca/revista")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(revistaDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(revistaCreada)));
    }


}

