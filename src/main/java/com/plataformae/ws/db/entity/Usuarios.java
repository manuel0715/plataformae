package com.plataformae.ws.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "usuarios", schema = "administrativo")
public class Usuarios implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    @Column(name = "identificacion", length = 20, nullable = false)
    private String identificacion;

    @Column(name = "estado", length = 1)
    @JsonIgnore
    private String estado;

    @Column(name = "tipo_identificacion", length = 4,  nullable = false )
    private String tipoIdentificacion;

    @Column(name = "primer_nombre", length = 100, nullable = false)
    private String primerNombre;

    @Column(name = "segundo_nombre", length = 100)
    private String segundoNombre;

    @Column(name = "primer_apellido", length = 100, nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 100)
    private String segundoApellido;

    @Column(name = "nombre_completo", length = 200, nullable = false)
    private String nombreCompleto;

    @Column(name = "fecha_expedicion_documento")
    private LocalDate fechaExpedicionDocumento;

    @Column(name = "fecha_vencimiento_documento")
    private LocalDate fechaVencimientoDocumento;

    @ManyToOne
    @JoinColumn(name = "departamento_expedicion_id", nullable = false)
    private Departamento departamentoExpedicion;

    @ManyToOne
    @JoinColumn(name = "municipio_expedicion_id", nullable = false)
    private Municipio municipioExpedicion;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "departamento_nacimiento_id", nullable = false)
    private Departamento departamentoNacimiento;

    @ManyToOne
    @JoinColumn(name = "municipio_nacimiento_id", nullable = false)
    private Municipio municipioNacimiento;

    @Column(name = "email", length = 80, unique = true, nullable = false)
    @NotBlank(message = "El email no puede estar vacío.")
    @Email(message = "El email debe ser válido.")
    private String email;

    @Column(name = "celular", length = 12, nullable = false, unique = true)
    private String celular;

    @Column(name = "tipo_usuario", length = 2, nullable = false)
    private String tipoUsuario;

    @Column(name = "usuario_creador", length = 15)
    private String usuarioCreador;

    @Column(name = "fecha_creacion")
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_ultima_modificacion", length = 15)
    @JsonIgnore
    private String usuarioUltimaModificacion;

    @Column(name = "fecha_ultima_modificacion")
    @JsonIgnore
    private LocalDateTime fechaUltimaModificacion;

    @Id
    @Column(unique = true, name = "usuario", nullable = false, length = 15)
    private String username;

    @Column(name = "contrasena", nullable = false, length = 32)
    @JsonIgnore
    private String password;

    @ManyToMany
    @JoinTable(
            name = "rel_roles_usuarios",
            schema = "administrativo",
            joinColumns = @JoinColumn(name = "usuario", referencedColumnName = "usuario"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;

    @Transient
    private String token;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFechaExpedicionDocumento() {
        return fechaExpedicionDocumento;
    }

    public void setFechaExpedicionDocumento(LocalDate fechaExpedicionDocumento) {
        this.fechaExpedicionDocumento = fechaExpedicionDocumento;
    }

    public LocalDate getFechaVencimientoDocumento() {
        return fechaVencimientoDocumento;
    }

    public void setFechaVencimientoDocumento(LocalDate fechaVencimientoDocumento) {
        this.fechaVencimientoDocumento = fechaVencimientoDocumento;
    }

    public Departamento getDepartamentoExpedicion() {
        return departamentoExpedicion;
    }

    public void setDepartamentoExpedicion(Departamento departamentoExpedicion) {
        this.departamentoExpedicion = departamentoExpedicion;
    }

    public Municipio getMunicipioExpedicion() {
        return municipioExpedicion;
    }

    public void setMunicipioExpedicion(Municipio municipioExpedicion) {
        this.municipioExpedicion = municipioExpedicion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Departamento getDepartamentoNacimiento() {
        return departamentoNacimiento;
    }

    public void setDepartamentoNacimiento(Departamento departamentoNacimiento) {
        this.departamentoNacimiento = departamentoNacimiento;
    }

    public Municipio getMunicipioNacimiento() {
        return municipioNacimiento;
    }

    public void setMunicipioNacimiento(Municipio municipioNacimiento) {
        this.municipioNacimiento = municipioNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioUltimaModificacion() {
        return usuarioUltimaModificacion;
    }

    public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }

    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    @PreUpdate
    protected void onSave() {
        // Establece la fecha de creación si es la primera vez que se guarda
        if (fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }

        // Convierte todos los campos String a mayúsculas
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)  && !field.getName().equals("password")) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(this);
                    if (value != null) {
                        field.set(this, value.toUpperCase());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Usuarios (){
        this.estado = "";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}