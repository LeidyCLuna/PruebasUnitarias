package com.sura.bibloteca.mapping;

import com.sura.bibloteca.dto.RevistaDto;
import com.sura.bibloteca.dto.RevistaIdDto;
import com.sura.bibloteca.dto.UsuarioDto;
import com.sura.bibloteca.entity.RevistaEntity;
import com.sura.bibloteca.entity.RevistaIdEntity;
import com.sura.bibloteca.entity.UsuarioEntity;

public class RevistaMapping {

    public RevistaEntity revistaDtoToRevistaEntity(RevistaDto revistaDto){
        return RevistaEntity.builder()
                .Id(revistaIdDtoToRevistaIdEntity(revistaDto.getId()))
                .numero(revistaDto.getNumero())
                .periocidad(revistaDto.getPeriocidad())
                .idElementoPrestableBase(new ElementoPrestableBaseMapping().ElementoPrestableBaseDtoToElementoPrestableBaseEntity(revistaDto.getIdElementoPrestableBase()))
                .build();
    }

    public RevistaDto revistaEntityToRevistaDto(RevistaEntity revistaEntity){
        return RevistaDto.builder()
                .Id(revistaIdEntityToRevistaIdDto(revistaEntity.getId()))
                .numero(revistaEntity.getNumero())
                .periocidad(revistaEntity.getPeriocidad())
                .idElementoPrestableBase(new ElementoPrestableBaseMapping().ElementoPrestableBaseEntityToElementoPrestableBaseDto(revistaEntity.getIdElementoPrestableBase()))
                .build();
    }

    public RevistaIdEntity revistaIdDtoToRevistaIdEntity(RevistaIdDto revistaIdDto){
        return RevistaIdEntity.builder()
                .idRevista(revistaIdDto.getIdRevista())
                .idElementoPrestableBase(revistaIdDto.getIdElementoPrestableBase()).build();
    }

    public RevistaIdDto revistaIdEntityToRevistaIdDto (RevistaIdEntity revistaIdEntity){
        return RevistaIdDto.builder()
                .idRevista(revistaIdEntity.getIdRevista())
                .idElementoPrestableBase(revistaIdEntity.getIdElementoPrestableBase())
                .build();
    }
}
