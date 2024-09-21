package com.plataformae.ws.dto;

public class MenuOpcionesDTO {
    private int id;
    private String nombreOpcion;
    private String opcion;
    private String icono;
    private int orden;

    public MenuOpcionesDTO(Integer id, String nombreOpcion, String pathOpcion, String icono, Integer orden) {
        this.id = id;
        this.nombreOpcion = nombreOpcion;
        this.opcion = pathOpcion;
        this.icono = icono;
        this.orden = orden;
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

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }
}
