package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rel_sede_carrera", schema = "configuracion")
public class RelSedeCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    private Carrera carrera;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}
