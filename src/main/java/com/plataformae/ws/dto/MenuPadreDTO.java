package com.plataformae.ws.dto;


import java.util.List;

public class MenuPadreDTO {

    private int id;
    private String icono;
    private int orden;
    private String menuPadre;
    private List<MenuOpcionesDTO> menuOpciones;

    public MenuPadreDTO(int id, String icono, int orden, String menuPadre, List<MenuOpcionesDTO> menuOpciones) {
        this.id = id;
        this.icono = icono;
        this.orden = orden;
        this.menuPadre = menuPadre;
        this.menuOpciones = menuOpciones;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(String menuPadre) {
        this.menuPadre = menuPadre;
    }

    public List<MenuOpcionesDTO> getMenuOpciones() {
        return menuOpciones;
    }

    public void setMenuOpciones(List<MenuOpcionesDTO> menuOpciones) {
        this.menuOpciones = menuOpciones;
    }
}
