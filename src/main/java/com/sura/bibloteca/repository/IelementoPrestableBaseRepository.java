package com.sura.bibloteca.repository;

import com.sura.bibloteca.entity.ElementoPrestableBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IelementoPrestableBaseRepository extends JpaRepository<ElementoPrestableBaseEntity,Integer> {
    ElementoPrestableBaseEntity findByTitulo(String titulo);

}
