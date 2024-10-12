package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rel_universidad_ciudad", schema = "configuracion")
public class RelUniversidadCiudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "universidad_id")
    private Universidad universidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "relUniversidadCiudad", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Sede> sede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Sede> getSede() {
        return sede;
    }

    public void setSede(List<Sede> sede) {
        this.sede = sede;
    }
}
