package com.sura.bibloteca.repository;

import com.sura.bibloteca.entity.RevistaEntity;
import com.sura.bibloteca.entity.RevistaIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrevistaRepository extends JpaRepository<RevistaEntity, RevistaIdEntity> {
}
