package com.plataformae.ws.dto;

public class MateriaDTO {
    private Long id;
    private String nombre;
    private CarreraDTO carrera;  // Relación ManyToOne
    private SedeDTO sede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CarreraDTO getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraDTO carrera) {
        this.carrera = carrera;
    }

    public SedeDTO getSede() {
        return sede;
    }

    public void setSede(SedeDTO sede) {
        this.sede = sede;
    }
}
