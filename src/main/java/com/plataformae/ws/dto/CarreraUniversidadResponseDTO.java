package com.plataformae.ws.dto;

import lombok.Data;

@Data
public class CarreraUniversidadResponseDTO {

    private Long universidadId;
    private String universidad;
    private String nit;
    private String tipoUniversidad;
    private String logo;
    private String terminosCondiciones;
    private String direccion;
    private String telefono;
    private String municipio;
    private Integer municipioId;
    private String departamento;
    private String carrera;
    private Integer carreraId;
    private String descripcion;
    private String pensum;
    private Double valorSemestre;
    private String tituloOtorgado;
    private String duracion;
    private String modalidad;
    private String colorModalidad;


    public CarreraUniversidadResponseDTO(Long universidadId, String universidad, String nit, String tipoUniversidad, String logo,
                                         String terminosCondiciones, String direccion, String telefono, String municipio,Integer municipioId,
                                         String departamento, String carrera,Integer carreraId, String descripcion, String pensum, Double valorSemestre, String tituloOtorgado, String duracion, String modalidad,String colorModalidad) {
        this.universidadId = universidadId;
        this.universidad = universidad;
        this.nit = nit;
        this.tipoUniversidad = tipoUniversidad;
        this.logo = logo;
        this.terminosCondiciones = terminosCondiciones;
        this.direccion = direccion;
        this.telefono = telefono;
        this.municipio = municipio;
        this.municipioId = municipioId;
        this.departamento = departamento;
        this.carrera = carrera;
        this.carreraId = carreraId;
        this.descripcion = descripcion;
        this.pensum = pensum;
        this.valorSemestre = valorSemestre;
        this.tituloOtorgado = tituloOtorgado;
        this.duracion = duracion;
        this.modalidad = modalidad;
        this.colorModalidad =colorModalidad;
    }


}
