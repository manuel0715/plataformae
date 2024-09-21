package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "menu_padre")
public class MenuPadre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombrePadre;

    @Column(length = 50, nullable = false)
    private String icono;

    @Column
    private Integer orden;

    @OneToMany(mappedBy = "menuPadre")
    private Set<MenuOpciones> menuOpciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Set<MenuOpciones> getMenuOpciones() {
        return menuOpciones;
    }

    public void setMenuOpciones(Set<MenuOpciones> menuOpciones) {
        this.menuOpciones = menuOpciones;
    }
}