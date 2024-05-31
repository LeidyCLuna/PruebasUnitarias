package com.sura.bibloteca.service.impl;

import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.dto.ElementoPrestamoDto;
import com.sura.bibloteca.entity.ElementoPrestamoEntity;
import com.sura.bibloteca.entity.UsuarioEntity;
import com.sura.bibloteca.mapping.ElementoPrestamoMapping;
import com.sura.bibloteca.mapping.UsuarioMapping;
import com.sura.bibloteca.repository.IelementoPrestamoRepository;
import com.sura.bibloteca.repository.IusuarioRepository;
import com.sura.bibloteca.service.IelementoPrestableBaseService;
import com.sura.bibloteca.service.IelementoPrestamoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ElementoPrestamoImpl implements IelementoPrestamoService {


    private final IelementoPrestableBaseService ielementoPrestableBaseService;
    private final IelementoPrestamoRepository ielementoPrestamoRepository;
    private final IusuarioRepository iusuarioRepository;
    @Override
    public ElementoPrestamoDto crearElementoPrestamo(ElementoPrestamoDto elementoPrestamoDTO) {

        if (validarUsuario(elementoPrestamoDTO)){
            ElementoPrestableBaseDTO inventarioBase = ielementoPrestableBaseService.buscarElementoPrestableBase(elementoPrestamoDTO.getIdElementoPrestableBaseDTO().getIdElementoPrestableBase());
            int inventario = restarInventario(inventarioBase.getInventario());
            inventarioBase.setInventario(inventario);
            ielementoPrestableBaseService.actualizarElementoPrestableBase(inventarioBase);
            ElementoPrestamoEntity elementoPrestamoGuardar = ielementoPrestamoRepository
                    .saveAndFlush(new ElementoPrestamoMapping().elementoPrestamoDtoToElementoPrestamoEntity(elementoPrestamoDTO));


            ElementoPrestamoDto elementoPrestamoDtoGuardado = new ElementoPrestamoMapping()
                                                            .elementoPrestamoEntityToElementoPrestamoDto(elementoPrestamoGuardar);
            return elementoPrestamoDtoGuardado;
        }

        return null;
    }
    @Override
    public ElementoPrestamoDto actualizarElementoPrestamo(ElementoPrestamoDto elementoPrestamoDTO) {
        return null;
    }

    @Override
    public ElementoPrestamoDto buscarElementoPrestamo(Integer idElementoPrestamoDTO) {
        return null;
    }

    @Override
    public String borrarElementoPrestamo(ElementoPrestamoDto elementoPrestamoDTO) {
        return null;
    }

    @Override
    public List<ElementoPrestamoDto> buscarTodosElementoPrestamoDtos() {
        return null;
    }

    private Boolean validarUsuario(ElementoPrestamoDto elementoPrestamoDTO){

        ElementoPrestableBaseDTO prestableBaseDTO = ielementoPrestableBaseService
                .buscarElementoPrestableBase(elementoPrestamoDTO
                        .getIdElementoPrestableBaseDTO()
                        .getIdElementoPrestableBase());

        if (Objects.nonNull(prestableBaseDTO) && prestableBaseDTO.getInventario()>0){
            List<Optional<ElementoPrestamoEntity>> elementoPrestamo = ielementoPrestamoRepository
                    .findByIdUsuarioAndFechaDevolucionIsNull( new UsuarioMapping().usuarioDtoToUsuarioEntity(elementoPrestamoDTO
                            .getIdUsuarioDto()));
            if (Objects.nonNull(elementoPrestamo) && elementoPrestamo.size()>=0){
                Optional<UsuarioEntity> usuarioEntity = iusuarioRepository.findById(elementoPrestamoDTO.getIdUsuarioDto().getIdUsuario());
                if (elementoPrestamo.size()<usuarioEntity.get().getLimitePrestamos()){
                    return Boolean.TRUE;
                }
            }

        }
        return Boolean.FALSE;
    }

    private Integer restarInventario(int invetario){
        return invetario- 1;
    }
    private Integer sumarInventario(int invetario){
        return invetario + 1;
    }
}
