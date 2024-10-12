package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sede", schema = "configuracion")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    private String estado = "";

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "rel_universidad_ciudad_id")
    private RelUniversidadCiudad relUniversidadCiudad;

    @OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RelSedeCarrera> relSedeCarrera; // Nueva relación

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

    public RelUniversidadCiudad getRelUniversidadCiudad() {
        return relUniversidadCiudad;
    }

    public void setRelUniversidadCiudad(RelUniversidadCiudad relUniversidadCiudad) {
        this.relUniversidadCiudad = relUniversidadCiudad;
    }

    public List<RelSedeCarrera> getRelSedeCarrera() {
        return relSedeCarrera;
    }

    public void setRelSedeCarrera(List<RelSedeCarrera> relSedeCarreras) {
        this.relSedeCarrera = relSedeCarreras;
    }
}
