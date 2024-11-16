package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones", schema = "gestion_academica")
public class Inscripciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuarios;

    @ManyToOne
    @JoinColumn(name = "universidad_id", nullable = false)
    private Universidad universidad;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "estado_proceso_id", nullable = false)
    private EstadoProceso estadoProceso;

    @ManyToOne
    @JoinColumn(name = "estado_contacto_id", nullable = false)
    private EstadoContacto estadoContacto;

    @Column(name = "fecha_ultima_gestion", nullable = false)
    private LocalDateTime fechaUltimaGestion = LocalDateTime.of(2000, 1, 1, 0, 0);

    @Column(name = "fecha_proxima_llamada", nullable = false)
    private LocalDateTime fechaProximaLlamada = LocalDateTime.of(2000, 1, 1, 0, 0);

    @Column(name = "ultimo_comentario_gestion", columnDefinition = "text")
    private String ultimoComentarioGestion;

    @Column(name = "usuario_ultima_gestion", nullable = false, length = 15)
    private String usuarioUltimaGestion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "anio_graduacion", nullable = false)
    private Integer anioGraduacion;

    @Column(name = "semestre_inicio_estudio", nullable = false)
    private Integer semestreInicioEstudio;

    @PrePersist
    private void setDefaultValues() {
        if (this.estadoProceso == null) {
            this.estadoProceso = new EstadoProceso();
            this.estadoProceso.setId(1);
        }

        if (this.estadoContacto == null) {
            this.estadoContacto = new EstadoContacto();
            this.estadoContacto.setId(1);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }


    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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

    public LocalDateTime getFechaUltimaGestion() {
        return fechaUltimaGestion;
    }

    public void setFechaUltimaGestion(LocalDateTime fechaUltimaGestion) {
        this.fechaUltimaGestion = fechaUltimaGestion;
    }

    public LocalDateTime getFechaProximaLlamada() {
        return fechaProximaLlamada;
    }

    public void setFechaProximaLlamada(LocalDateTime fechaProximaLlamada) {
        this.fechaProximaLlamada = fechaProximaLlamada;
    }

    public String getUltimoComentarioGestion() {
        return ultimoComentarioGestion;
    }

    public void setUltimoComentarioGestion(String ultimoComentarioGestion) {
        this.ultimoComentarioGestion = ultimoComentarioGestion;
    }

    public String getUsuarioUltimaGestion() {
        return usuarioUltimaGestion;
    }

    public void setUsuarioUltimaGestion(String usuarioUltimaGestion) {
        this.usuarioUltimaGestion = usuarioUltimaGestion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getAnioGraduacion() {
        return anioGraduacion;
    }

    public void setAnioGraduacion(Integer anioGraduacion) {
        this.anioGraduacion = anioGraduacion;
    }

    public Integer getSemestreInicioEstudio() {
        return semestreInicioEstudio;
    }

    public void setSemestreInicioEstudio(Integer semestreInicioEstudio) {
        this.semestreInicioEstudio = semestreInicioEstudio;
    }
}
