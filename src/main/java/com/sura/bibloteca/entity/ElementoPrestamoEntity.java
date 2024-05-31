package com.sura.bibloteca.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "elemento_prestamo")
public class ElementoPrestamoEntity {

    @Id
    @Column(name = "id_prestamo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_elemento_prestable_base", nullable = false)
    private ElementoPrestableBaseEntity idElementoPrestableBase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    @Fetch(FetchMode.JOIN)
    private EmpleadoEntity idEmpleado;

    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

}
