package com.plataformae.ws.dto;

import java.time.LocalDate;

public class CargarInscripcionesRequestDTO {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
