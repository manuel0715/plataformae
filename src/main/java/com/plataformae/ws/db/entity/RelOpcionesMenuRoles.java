package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rel_opciones_menu_roles", schema = "administrativo")
public class RelOpcionesMenuRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "menu_opciones_id", nullable = false)
    private MenuOpciones menuOpciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public MenuOpciones getMenuOpciones() {
        return menuOpciones;
    }

    public void setMenuOpciones(MenuOpciones menuOpciones) {
        this.menuOpciones = menuOpciones;
    }
}
