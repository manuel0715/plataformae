package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "estado_proceso", schema = "configuracion")
public class EstadoProceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "estado_contacto_id")
    @JsonIgnore
    private EstadoContacto estadoContacto;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoContacto getEstadoContacto() {
        return estadoContacto;
    }

    public void setEstadoContacto(EstadoContacto estadoContacto) {
        this.estadoContacto = estadoContacto;
    }
}
