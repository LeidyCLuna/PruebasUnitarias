package com.sura.bibloteca.mapping;

import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import com.sura.bibloteca.util.LocalDateFomatter;

import java.util.ArrayList;
import java.util.List;


public class ElementoPrestableBaseMapping {


    public ElementoPrestableBaseEntity ElementoPrestableBaseDtoToElementoPrestableBaseEntity(ElementoPrestableBaseDTO elementoPrestableBaseDTO){
        return ElementoPrestableBaseEntity.builder()
                .idElementoPrestableBase(elementoPrestableBaseDTO.getIdElementoPrestableBase())
                .tipoElementoPrestable(elementoPrestableBaseDTO.getTipoElementoPrestable())
                .genero(elementoPrestableBaseDTO.getGenero())
                .categoria(elementoPrestableBaseDTO.getCategoria())
                .inventario(elementoPrestableBaseDTO.getInventario())
                .titulo(elementoPrestableBaseDTO.getTitulo())
                .anoPublicacion(new LocalDateFomatter().dateStringtoLocalDate(elementoPrestableBaseDTO.getAnoPublicacion()))
                .build();
    }
    public ElementoPrestableBaseDTO ElementoPrestableBaseEntityToElementoPrestableBaseDto(ElementoPrestableBaseEntity elementoPrestableBaseEntity){
        return ElementoPrestableBaseDTO.builder()
                .idElementoPrestableBase(elementoPrestableBaseEntity.getIdElementoPrestableBase())
                .anoPublicacion(new LocalDateFomatter().LocalDateToStringDate(elementoPrestableBaseEntity.getAnoPublicacion()))
                .categoria(elementoPrestableBaseEntity.getCategoria())
                .genero(elementoPrestableBaseEntity.getGenero())
                .inventario(elementoPrestableBaseEntity.getInventario())
                .tipoElementoPrestable(elementoPrestableBaseEntity.getTipoElementoPrestable())
                .titulo(elementoPrestableBaseEntity.getTitulo())
                .build();
    }

    public List<ElementoPrestableBaseDTO> listElementoPrestableBaseEntityToElementoPrestableBaseDto(List<ElementoPrestableBaseEntity> entities){
        List<ElementoPrestableBaseDTO> elementoPrestableBaseDTOS = new ArrayList<>();
        entities.forEach(elementoPrestableBaseEntity -> {
            ElementoPrestableBaseDTO prestableBaseDTO = ElementoPrestableBaseEntityToElementoPrestableBaseDto(elementoPrestableBaseEntity);
            elementoPrestableBaseDTOS.add(prestableBaseDTO);
        });
        return elementoPrestableBaseDTOS;
    }
}
