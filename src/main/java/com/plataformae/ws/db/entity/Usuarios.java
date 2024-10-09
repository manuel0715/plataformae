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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "usuarios", schema = "administrativo")
public class Usuarios implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion", length = 20, nullable = false)
    private String identificacion;

    @Column(name = "estado", length = 1)
    @JsonIgnore
    private String estado;

    @Column(name = "tipo_identificacion", length = 4,  nullable = false )
    private String tipoIdentificacion;

    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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