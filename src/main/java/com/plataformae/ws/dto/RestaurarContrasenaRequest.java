package com.plataformae.ws.dto;

public class RestaurarContrasenaRequest {

    private String identificacion;
    private String tipoIdentificacion;
    private String otp;
    private String nuevaContrasena;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    public void setNuevaContrasena(String nuevaContrasena) {
        this.nuevaContrasena = nuevaContrasena;
    }
}
