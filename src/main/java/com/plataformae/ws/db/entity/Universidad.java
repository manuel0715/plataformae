package com.plataformae.ws.db.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "universidad", fetch = FetchType.LAZY)
    private List<RelUniversidadCiudad> relUniversidadCiudad;



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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTipoUniversidad() {
        return tipoUniversidad;
    }

    public void setTipoUniversidad(String tipoUniversidad) {
        this.tipoUniversidad = tipoUniversidad;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioUltimaModificacion() {
        return usuarioUltimaModificacion;
    }

    public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }

    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public List<RelUniversidadCiudad> getRelUniversidadCiudades() {
        return relUniversidadCiudad;
    }

    public void setRelUniversidadCiudades(List<RelUniversidadCiudad> relUniversidadCiudad) {
        this.relUniversidadCiudad = relUniversidadCiudad;
    }


}
