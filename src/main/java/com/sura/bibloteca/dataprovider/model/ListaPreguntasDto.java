package com.sura.bibloteca.dataprovider.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListaPreguntasDto implements Serializable {

    private List<PreguntaDto> preguntas;

    private String fechaRegistro;

    private int idPregunta;
}
