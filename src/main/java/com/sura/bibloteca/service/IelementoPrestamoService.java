package com.sura.bibloteca.service;



import com.sura.bibloteca.dto.ElementoPrestamoDto;

import java.util.List;

public interface IelementoPrestamoService {

    ElementoPrestamoDto crearElementoPrestamo(ElementoPrestamoDto elementoPrestamoDTO);
    ElementoPrestamoDto actualizarElementoPrestamo(ElementoPrestamoDto elementoPrestamoDTO);

    ElementoPrestamoDto buscarElementoPrestamo(Integer idElementoPrestamoDTO);
    String borrarElementoPrestamo(ElementoPrestamoDto elementoPrestamoDTO);

    List<ElementoPrestamoDto> buscarTodosElementoPrestamoDtos();

}
