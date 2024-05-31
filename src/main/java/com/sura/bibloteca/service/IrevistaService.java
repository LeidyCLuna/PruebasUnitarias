package com.sura.bibloteca.service;

import com.sura.bibloteca.dto.ElementoPrestamoDto;
import com.sura.bibloteca.dto.RevistaDto;
import com.sura.bibloteca.dto.RevistaIdDto;
import org.springframework.stereotype.Service;

import javax.naming.NotContextException;
import java.util.List;
@Service
public interface IrevistaService {
    RevistaDto crearRevista(RevistaDto revistaDto) throws NotContextException;
    RevistaDto actualizarRevista(RevistaDto revistaDto);
    RevistaDto buscarRevista(RevistaIdDto revistaIdDto);
    String borrarRevista(RevistaDto revistaDto);
    List<RevistaDto> buscarTodasRevistas();
}
