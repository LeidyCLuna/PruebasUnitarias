package com.sura.bibloteca.mapping;

import com.sura.bibloteca.dto.UsuarioDto;
import com.sura.bibloteca.entity.UsuarioEntity;

public class UsuarioMapping {

    public UsuarioEntity usuarioDtoToUsuarioEntity(UsuarioDto usuarioDto){
        return UsuarioEntity.builder()
                .id(usuarioDto.getIdUsuario())
                .limitePrestamos(usuarioDto.getLimitePrestamos())
                .build();
    }

    public UsuarioDto usuarioEntityToUsuarioDto(UsuarioEntity usuarioEntity){
        return UsuarioDto.builder()
                .idUsuario(usuarioEntity.getId())
                .limitePrestamos(usuarioEntity.getLimitePrestamos())
                .build();
    }
}
