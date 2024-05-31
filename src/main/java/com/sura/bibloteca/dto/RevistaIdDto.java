package com.sura.bibloteca.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RevistaIdDto {
    @JsonProperty("id_revista")
    private Integer idRevista;

    @JsonProperty("id_elemento_prestable_base")
    private Integer idElementoPrestableBase;
}
