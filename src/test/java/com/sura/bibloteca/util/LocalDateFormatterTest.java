package com.sura.bibloteca.util;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class LocalDateFormatterTest {

    @InjectMocks
    LocalDateFomatter localDateFomatter;

    @Test
    void TestDateStringtoLocalDate() {
        // arrange
        String fecha = "02/11/2023";
        LocalDate fechaEsperada = LocalDate.of(2023, 11, 2);

        //act
        LocalDate fechaFormateada = localDateFomatter.dateStringtoLocalDate(fecha);

        //assert
        assertEquals(fechaEsperada,fechaFormateada);

    }

    @Test
    void TestDateStringtoLocalDateNull() {
        // arrange
        String fecha = null;

        //act
        LocalDate fechaFormateada = localDateFomatter.dateStringtoLocalDate(fecha);

        //assert
        assertNull(fechaFormateada);

    }

    @Test
    void TestLocalDateToStringDate() {
        // arrange
        LocalDate fecha = LocalDate.of(2023, 11, 2);
        String fechaEsperada = "02/11/2023";

        //act
        String fechaFormateada = localDateFomatter.LocalDateToStringDate(fecha);

        //assert
        assertEquals(fechaEsperada,fechaFormateada);

    }

    @Test
    void TestLocalDateToStringDateNull() {
        // arrange
        LocalDate fecha = null;

        //act
        String fechaFormateada = localDateFomatter.LocalDateToStringDate(fecha);

        //assert
        assertNull(fechaFormateada);

    }

}
