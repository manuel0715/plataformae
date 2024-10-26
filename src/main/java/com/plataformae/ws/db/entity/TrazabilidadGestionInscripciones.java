package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "trazabilidad_gestion_inscripciones", schema = "gestion_academica")
public class TrazabilidadGestionInscripciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estado", nullable = false)
    private String estado="";

    @ManyToOne
    @JoinColumn(name = "incripcion_id", nullable = false)
    private Inscripciones inscripciones;

    @ManyToOne
    @JoinColumn(name = "estado_proceso_id", nullable = false)
    private EstadoProceso estadoProceso;

    @ManyToOne
    @JoinColumn(name = "estado_contacto_id", nullable = false)
    private EstadoContacto estadoContacto;

    @Column(name = "fecha_gestion", nullable = false)
    private LocalDateTime fechaGestion =  LocalDateTime.now();

    @Column(name = "fecha_proxima_llamada", nullable = false)
    private LocalDateTime fechaProximaLlamada = LocalDateTime.of(2000, 1, 1,0,0);

    @Column(name = "comentario_gestion", columnDefinition = "text")
    private String comentarioGestion;

    @ManyToOne
    @JoinColumn(name = "usuario_gestion", nullable = false)
    private Usuarios usuarioGestion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Inscripciones getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Inscripciones inscripciones) {
        this.inscripciones = inscripciones;
    }

    public EstadoProceso getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(EstadoProceso estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public EstadoContacto getEstadoContacto() {
        return estadoContacto;
    }

    public void setEstadoContacto(EstadoContacto estadoContacto) {
        this.estadoContacto = estadoContacto;
    }

    public LocalDateTime getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(LocalDateTime fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public LocalDateTime getFechaProximaLlamada() {
        return fechaProximaLlamada;
    }

    public void setFechaProximaLlamada(LocalDateTime fechaProximaLlamada) {
        this.fechaProximaLlamada = fechaProximaLlamada;
    }

    public String getComentarioGestion() {
        return comentarioGestion;
    }

    public void setComentarioGestion(String comentarioGestion) {
        this.comentarioGestion = comentarioGestion;
    }

    public Usuarios getUsuarioGestion() {
        return usuarioGestion;
    }

    public void setUsuarioGestion(Usuarios usuarioGestion) {
        this.usuarioGestion = usuarioGestion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
