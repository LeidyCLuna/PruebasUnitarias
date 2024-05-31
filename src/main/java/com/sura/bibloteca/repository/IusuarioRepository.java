package com.sura.bibloteca.repository;

import com.sura.bibloteca.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IusuarioRepository extends JpaRepository<UsuarioEntity,Integer> {
}
