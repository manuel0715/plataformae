package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "administrativo")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String rol;

    @ManyToMany
    @JoinTable(
            name = "rel_opciones_menu_roles",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_opciones_id")
    )
    private Set<MenuOpciones> menuOpciones;

    public Set<MenuOpciones> getMenuOpciones() {
        return menuOpciones;
    }

    public void setMenuOpciones(Set<MenuOpciones> menuOpciones) {
        this.menuOpciones = menuOpciones;
    }

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
