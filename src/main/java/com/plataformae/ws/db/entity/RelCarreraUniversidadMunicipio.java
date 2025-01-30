package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rel_carrera_universidad_municipio", schema = "configuracion")
public class RelCarreraUniversidadMunicipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "universidad_id")
    private Universidad universidad;

    @Column(nullable = false, length = 255)
    private String nombreUniversidad;

    @ManyToOne
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    @Column(nullable = false, length = 255)
    private String nombreCarrera;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, length = 255)
    private String pensum;

    @Column(nullable = false)
    private BigDecimal valorSemestre;

    @Column(nullable = false, length = 255)
    private String tituloOtorgado;

    @Column(nullable = false, length = 255)
    private Integer duracion;

    @ManyToOne
    @JoinColumn(name = "modalidad_id")
    private Modalidad modalidad;


}
