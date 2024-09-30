package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "sede", schema = "configuracion")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    private String estado = "";

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "universidad_id", referencedColumnName = "id")
    private Universidad universidad;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    private Ciudad ciudad;


    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @OneToMany(mappedBy = "sede")
    private List<Carrera> carrera; // Añadir esta línea para relacionar las carreras


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Carrera> getCarreras() {
        return carrera;
    }

    public void setCarreras(List<Carrera> carrera) {
        this.carrera = carrera;
    }


}
