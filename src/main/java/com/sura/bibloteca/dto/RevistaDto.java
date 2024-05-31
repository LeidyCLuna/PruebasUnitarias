package com.sura.bibloteca.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import com.sura.bibloteca.entity.RevistaIdEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RevistaDto {
    private RevistaIdDto Id;

    private Integer numero;

    private String periocidad;

    @JsonProperty("id_elemento_prestable_base")
    private ElementoPrestableBaseDTO idElementoPrestableBase;
}
