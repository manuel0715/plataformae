package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rel_universidad_municipio", schema = "configuracion")
public class RelUniversidadMunicipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "universidad_id")
    private Universidad universidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @OneToMany(mappedBy = "relUniversidadMunicipio", fetch = FetchType.LAZY)
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<Sede> getSede() {
        return sede;
    }

    public void setSede(List<Sede> sede) {
        this.sede = sede;
    }
}
