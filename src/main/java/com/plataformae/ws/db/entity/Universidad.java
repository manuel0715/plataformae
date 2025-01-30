package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "universidad", schema = "configuracion")
public class Universidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    private String estado = "";

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, length = 15, unique = true)
    private String nit;

    @Column(name = "tipo_universidad", nullable = false, length = 50)
    private String tipoUniversidad = "";

    @Column(name = "usuario_creador", nullable = false, length = 15)
    private String usuarioCreador = "";

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "usuario_ultima_modificacion", length = 15)
    private String usuarioUltimaModificacion = "";

    @Column(name = "fecha_ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion = LocalDateTime.of(2000, 1, 1, 0, 0);

    @OneToMany(mappedBy = "universidad")
    @JsonIgnore
    private List<RelCarreraUniversidadMunicipio> relCarreraUniversidadMunicipio;

    @Column(name = "logo")
    private String logo = "";

    @Column(name = "terminos_condiciones")
    private String terminosCondiciones = "";

    @Column(name = "direccion")
    private String direccion = "";

    @Column(name = "telefono")
    private String telefono = "";


}
