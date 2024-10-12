package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "carrera", schema = "configuracion")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RelCarreraMateria> relCarreraMateria;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RelSedeCarrera> relSedeCarrera; // Nueva relación

    private Integer duracion; // duración en semestres

    private String tipo; // pregrado, posgrado, etc.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<RelCarreraMateria> getRelCarreraMaterias() {
        return relCarreraMateria;
    }

    public void setRelCarreraMaterias(List<RelCarreraMateria> relCarreraMaterias) {
        this.relCarreraMateria = relCarreraMaterias;
    }

    public List<RelSedeCarrera> getRelSedeCarreras() {
        return relSedeCarrera;
    }

    public void setRelSedeCarreras(List<RelSedeCarrera> relSedeCarreras) {
        this.relSedeCarrera = relSedeCarreras;
    }
}