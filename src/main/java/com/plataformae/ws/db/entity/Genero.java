package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "genero", schema = "configuracion")
public class Genero {

    @Id
    private String id;
    private String estado="";
    private String nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
