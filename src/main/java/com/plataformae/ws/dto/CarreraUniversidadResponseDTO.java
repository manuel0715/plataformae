package com.plataformae.ws.dto;

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
    private String departamento;
    private String carrera;
    private String descripcion;
    private String pensum;
    private Double valorSemestre;
    private String tituloOtorgado;
    private String duracion;
    private String modalidad;

    public CarreraUniversidadResponseDTO(Long universidadId, String universidad, String nit, String tipoUniversidad, String logo, String terminosCondiciones, String direccion, String telefono, String municipio, String departamento, String carrera, String descripcion, String pensum, Double valorSemestre, String tituloOtorgado, String duracion, String modalidad) {
        this.universidadId = universidadId;
        this.universidad = universidad;
        this.nit = nit;
        this.tipoUniversidad = tipoUniversidad;
        this.logo = logo;
        this.terminosCondiciones = terminosCondiciones;
        this.direccion = direccion;
        this.telefono = telefono;
        this.municipio = municipio;
        this.departamento = departamento;
        this.carrera = carrera;
        this.descripcion = descripcion;
        this.pensum = pensum;
        this.valorSemestre = valorSemestre;
        this.tituloOtorgado = tituloOtorgado;
        this.duracion = duracion;
        this.modalidad = modalidad;
    }

    public Long getUniversidadId() {
        return universidadId;
    }

    public void setUniversidadId(Long universidadId) {
        this.universidadId = universidadId;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTipoUniversidad() {
        return tipoUniversidad;
    }

    public void setTipoUniversidad(String tipoUniversidad) {
        this.tipoUniversidad = tipoUniversidad;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTerminosCondiciones() {
        return terminosCondiciones;
    }

    public void setTerminosCondiciones(String terminosCondiciones) {
        this.terminosCondiciones = terminosCondiciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPensum() {
        return pensum;
    }

    public void setPensum(String pensum) {
        this.pensum = pensum;
    }

    public Double getValorSemestre() {
        return valorSemestre;
    }

    public void setValorSemestre(Double valorSemestre) {
        this.valorSemestre = valorSemestre;
    }

    public String getTituloOtorgado() {
        return tituloOtorgado;
    }

    public void setTituloOtorgado(String tituloOtorgado) {
        this.tituloOtorgado = tituloOtorgado;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
}
