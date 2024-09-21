package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "menu_opciones", schema = "administrativo")
public class MenuOpciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String nombreOpcion;

    @Column(length = 100, nullable = false, unique = true)
    private String pathOpcion;

    @Column(length = 50, nullable = false)
    private String icono;

    @ManyToOne
    @JoinColumn(name = "menu_padre_id")
    private MenuPadre menuPadre;

    @Column
    private Integer orden;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    public String getPathOpcion() {
        return pathOpcion;
    }

    public void setPathOpcion(String pathOpcion) {
        this.pathOpcion = pathOpcion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public MenuPadre getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(MenuPadre menuPadre) {
        this.menuPadre = menuPadre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

}
