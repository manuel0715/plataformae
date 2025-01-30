package com.plataformae.ws.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "municipio", schema = "configuracion")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    private String estado = "";

    @Column(length = 10)
    private String abreviatura;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public Municipio() {
    }

    public Municipio(Long id) {
        this.id = id;
    }

}
