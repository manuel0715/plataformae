package com.plataformae.ws.db.entity;

import jakarta.persistence.*;


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

    @ManyToOne
    @JoinColumn(name = "universidad_id", nullable = false)
    private Universidad universidad;

    @ManyToOne
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede; // Relación con Sede

    @ManyToOne
    @JoinColumn(name = "semestre_id", nullable = false)
    private Semestre semestre;

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

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
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
}