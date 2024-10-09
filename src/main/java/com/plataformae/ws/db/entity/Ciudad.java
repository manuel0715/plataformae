package com.plataformae.ws.db.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ciudad", schema = "configuracion")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    private String estado = "";

    @Column(length = 10)
    private String abreviatura;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY)
    private List<RelUniversidadCiudad> relUniversidadCiudades;



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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
