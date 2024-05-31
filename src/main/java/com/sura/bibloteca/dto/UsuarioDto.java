package com.sura.bibloteca.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    @JsonProperty(value = "id_usuario")
    private Integer idUsuario;

    @JsonProperty(value = "limite_prestamos")
    private Integer limitePrestamos;
}
