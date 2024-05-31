package com.sura.bibloteca.repository;

import com.sura.bibloteca.entity.ElementoPrestamoEntity;
import com.sura.bibloteca.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IelementoPrestamoRepository extends JpaRepository<ElementoPrestamoEntity,Integer> {

    List<Optional<ElementoPrestamoEntity>> findByIdUsuarioAndFechaDevolucionIsNull(UsuarioEntity usuarioEntity);

}
