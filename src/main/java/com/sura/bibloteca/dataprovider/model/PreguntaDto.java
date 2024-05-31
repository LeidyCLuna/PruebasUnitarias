package com.sura.bibloteca.dataprovider.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaDto {

    private List<OpcionesPreguntaDto> opcionesPreguntaDtos;
    private String tipoPregunta;
    private String pregunta;





}
