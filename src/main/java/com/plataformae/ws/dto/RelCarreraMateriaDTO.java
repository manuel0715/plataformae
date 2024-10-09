package com.plataformae.ws.dto;

public class RelCarreraMateriaDTO {

    private Long id;
    private Long carreraId; // Referencia al ID de la carrera
    private Long materiaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Long carreraId) {
        this.carreraId = carreraId;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }
}
