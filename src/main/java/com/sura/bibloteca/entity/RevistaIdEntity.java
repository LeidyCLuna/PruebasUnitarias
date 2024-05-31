package com.sura.bibloteca.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class RevistaIdEntity{
    @Column(name = "id_revista",nullable = false)
    private Integer idRevista;

    @Column(name = "id_elemento_prestable_base",nullable = false)
    private Integer idElementoPrestableBase;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RevistaIdEntity entity = (RevistaIdEntity) o;
        return Objects.equals(this.idRevista, entity.idRevista) &&
                Objects.equals(this.idElementoPrestableBase, entity.idElementoPrestableBase);
    }


    @Override
    public int hashCode() {
        return Objects.hash(idRevista, idElementoPrestableBase);
    }

}
