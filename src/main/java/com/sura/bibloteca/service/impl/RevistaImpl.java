package com.sura.bibloteca.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.dto.RevistaDto;
import com.sura.bibloteca.dto.RevistaIdDto;
import com.sura.bibloteca.entity.RevistaEntity;
import com.sura.bibloteca.mapping.RevistaMapping;
import com.sura.bibloteca.repository.IrevistaRepository;
import com.sura.bibloteca.service.IelementoPrestableBaseService;
import com.sura.bibloteca.service.IelementoPrestamoService;
import com.sura.bibloteca.service.IrevistaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.NotContextException;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class RevistaImpl implements IrevistaService {
    private final IelementoPrestableBaseService ielementoPrestableBaseService;
    private final IrevistaRepository irevistaRepository;

    @Override
    public RevistaDto crearRevista(RevistaDto revistaDto) throws NotContextException {
        try {
            //Consultar si existe elemento prestable base
            ElementoPrestableBaseDTO elementoPrestableBaseDTO = ielementoPrestableBaseService.buscarElementoPrestableBase(revistaDto.getId().getIdElementoPrestableBase());


            if (Objects.isNull(elementoPrestableBaseDTO)) {
                throw new NotContextException("Debe crear el elemento prestable base");
            }

            //Crear Revista
            RevistaEntity revistaEntity = irevistaRepository.saveAndFlush(new RevistaMapping().revistaDtoToRevistaEntity(revistaDto));
            return new RevistaMapping().revistaEntityToRevistaDto(revistaEntity);

        } catch (Exception exception) {
            throw new NotContextException(exception.getMessage());
        }


    }

    @Override
    public RevistaDto actualizarRevista(RevistaDto revistaDto) {
        return null;
    }

    @Override
    public RevistaDto buscarRevista(RevistaIdDto revistaIdDto) {
        return null;
    }

    @Override
    public String borrarRevista(RevistaDto revistaDto) {
        return null;
    }

    @Override
    public List<RevistaDto> buscarTodasRevistas() {
        return null;
    }
}
