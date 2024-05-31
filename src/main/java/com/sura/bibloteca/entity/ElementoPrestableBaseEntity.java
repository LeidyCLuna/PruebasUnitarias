package com.sura.bibloteca.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "elemento_prestable_base")
public class ElementoPrestableBaseEntity {

    @Id
    @Column(name = "id_elemento_prestable_base",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idElementoPrestableBase;

    @Column(name = "tipo_elemento_prestable",nullable = false)
    private String tipoElementoPrestable;

    @Column(name = "titulo",nullable = false)
    private String titulo;

    @Column(name = "genero",nullable = false)
    private String genero;

    @Column(name = "inventario",nullable = false)
    private Integer inventario;

    @Column(name = "ano_publicacion",nullable = false)
    private LocalDate anoPublicacion;

    @Column(name = "categoria",nullable = false)
    private String categoria;

}
