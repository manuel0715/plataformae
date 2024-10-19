package com.plataformae.ws.dto;

public class OtpResponseDTO {

   private String mensaje;
   private String email;
   private int tiempoExpiracion;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTiempoExpiracion() {
        return tiempoExpiracion;
    }

    public void setTiempoExpiracion(int tiempoExpiracion) {
        this.tiempoExpiracion = tiempoExpiracion;
    }
}
