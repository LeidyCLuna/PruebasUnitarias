package com.sura.bibloteca.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "revista")
public class RevistaEntity {

    @EmbeddedId
    private RevistaIdEntity Id;

    @Column(name = "numero",nullable = false)
    private Integer numero;

    @Column(name = "periocidad",nullable = false,length = 50)
    private String periocidad;

    @MapsId("idElementoPrestableBase")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_elemento_prestable_base", nullable = false)
    private ElementoPrestableBaseEntity idElementoPrestableBase;
}
