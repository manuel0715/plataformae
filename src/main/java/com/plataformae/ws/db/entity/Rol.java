package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
