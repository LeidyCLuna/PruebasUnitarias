package com.sura.bibloteca.service;


import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;

import java.util.List;

public interface IelementoPrestableBaseService {

    ElementoPrestableBaseDTO crearElementoPrestableBase(ElementoPrestableBaseDTO elementoPrestableBaseDTO);
    ElementoPrestableBaseDTO actualizarElementoPrestableBase(ElementoPrestableBaseDTO elementoPrestableBaseDTO);

    ElementoPrestableBaseDTO buscarElementoPrestableBase(Integer idElementoPrestableBaseDTO);
    String borrarElementoPrestableBase(ElementoPrestableBaseDTO elementoPrestableBaseDTO);

    List<ElementoPrestableBaseDTO> buscarTodosElementosPestablesBase();


}
