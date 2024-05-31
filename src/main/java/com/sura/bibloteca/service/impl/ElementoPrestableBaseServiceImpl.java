package com.sura.bibloteca.service.impl;

import com.sura.bibloteca.dataprovider.client.IladiscordiaClient;
import com.sura.bibloteca.dataprovider.model.ListaPreguntasDto;
import com.sura.bibloteca.dataprovider.model.OpcionesPreguntaDto;
import com.sura.bibloteca.dataprovider.model.PreguntaDto;
import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import com.sura.bibloteca.mapping.ElementoPrestableBaseMapping;
import com.sura.bibloteca.repository.IelementoPrestableBaseRepository;
import com.sura.bibloteca.service.IelementoPrestableBaseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElementoPrestableBaseServiceImpl implements IelementoPrestableBaseService {


    private final IelementoPrestableBaseRepository elementoPrestableBaseRepository;
    private final IladiscordiaClient iladiscordiaClient;

    @Override
    public ElementoPrestableBaseDTO crearElementoPrestableBase(ElementoPrestableBaseDTO elementoPrestableBaseDTO) {
        ElementoPrestableBaseEntity buscarTitulo = elementoPrestableBaseRepository.findByTitulo(elementoPrestableBaseDTO.getTitulo());
        if (Objects.isNull(buscarTitulo)){
            iladiscordiaClient.guardarPregunta(guardarListaPregunta());
            ElementoPrestableBaseEntity crearElemento = elementoPrestableBaseRepository.saveAndFlush(new ElementoPrestableBaseMapping().ElementoPrestableBaseDtoToElementoPrestableBaseEntity(elementoPrestableBaseDTO));
            if (Objects.nonNull(crearElemento)){
                 ElementoPrestableBaseDTO prestableBaseDTO =  new ElementoPrestableBaseMapping()
                                                                    .ElementoPrestableBaseEntityToElementoPrestableBaseDto(crearElemento);
                return prestableBaseDTO;
            }
        }
        return null;

    }

    ListaPreguntasDto guardarListaPregunta(){
        List<PreguntaDto> preguntas = new ArrayList<>();
        List<OpcionesPreguntaDto> preguntasDto = new ArrayList<>();
        preguntasDto.add(OpcionesPreguntaDto.builder().id("R").value("").build());
        PreguntaDto preguntaDto = PreguntaDto.builder().pregunta("Como vamos con la clase?").tipoPregunta("Abierta").opcionesPreguntaDtos(preguntasDto).build();
        preguntas.add(preguntaDto);
        String fechaRegistro = "01/04/2024";
        return ListaPreguntasDto.builder().preguntas(preguntas).fechaRegistro(fechaRegistro).build();
    }

    @Override
    public ElementoPrestableBaseDTO actualizarElementoPrestableBase(ElementoPrestableBaseDTO elementoPrestableBaseDTO) {
        Optional<ElementoPrestableBaseEntity> buscarElemento = elementoPrestableBaseRepository.findById(elementoPrestableBaseDTO.getIdElementoPrestableBase());
        if (Objects.nonNull(buscarElemento.get())){
            ElementoPrestableBaseEntity actualizarElemento = elementoPrestableBaseRepository.saveAndFlush(new ElementoPrestableBaseMapping()
                                                                                    .ElementoPrestableBaseDtoToElementoPrestableBaseEntity(elementoPrestableBaseDTO) );
            if (Objects.nonNull(actualizarElemento)){
                ElementoPrestableBaseDTO actualizarElementoPrestableBaseDto = new ElementoPrestableBaseMapping().ElementoPrestableBaseEntityToElementoPrestableBaseDto(actualizarElemento);
                return actualizarElementoPrestableBaseDto;
            }
        }
        return null;
    }
    @Override
    public ElementoPrestableBaseDTO buscarElementoPrestableBase(Integer idElementoPrestableBaseDTO) {
        Optional<ElementoPrestableBaseEntity> buscarElemento = elementoPrestableBaseRepository.findById(idElementoPrestableBaseDTO);
        if (buscarElemento.isPresent()){
            ElementoPrestableBaseDTO buscarElementoPrestableDto = new ElementoPrestableBaseMapping().ElementoPrestableBaseEntityToElementoPrestableBaseDto(buscarElemento.get());
            return  buscarElementoPrestableDto;
        }
        return null;
    }

    @Override
    public String borrarElementoPrestableBase(ElementoPrestableBaseDTO elementoPrestableBaseDTO) {
        ElementoPrestableBaseDTO buscarElementoPrestableDto = buscarElementoPrestableBase(elementoPrestableBaseDTO.getIdElementoPrestableBase());
        if (Objects.nonNull(buscarElementoPrestableDto)){
            elementoPrestableBaseRepository.delete(new ElementoPrestableBaseMapping().ElementoPrestableBaseDtoToElementoPrestableBaseEntity(elementoPrestableBaseDTO));
            return  "EL elemento fue borrado exitosamente";
        }
        return null;
    }

    @Override
    public List<ElementoPrestableBaseDTO> buscarTodosElementosPestablesBase() {
         List<ElementoPrestableBaseEntity> elementoPrestableBaseEntity = elementoPrestableBaseRepository.findAll();
         if (!elementoPrestableBaseEntity.isEmpty() && elementoPrestableBaseEntity.size()>0){
             List<ElementoPrestableBaseDTO> elementoPrestableBaseDTOS = new ElementoPrestableBaseMapping().
                     listElementoPrestableBaseEntityToElementoPrestableBaseDto(elementoPrestableBaseEntity);
             return elementoPrestableBaseDTOS;
         }
         return null;
    }
}
