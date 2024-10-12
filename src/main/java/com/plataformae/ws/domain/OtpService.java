package com.plataformae.ws.domain;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private String generatedOtp;
    private LocalDateTime expiryTime;

    // Generar OTP
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // OTP de 6 dígitos
        generatedOtp = String.valueOf(otp);
        expiryTime = LocalDateTime.now().plusMinutes(5); // Expira en 5 minutos
        return generatedOtp;
    }

    // Validar OTP
    public boolean validateOtp(String inputOtp) {
        return inputOtp.equals(generatedOtp) && LocalDateTime.now().isBefore(expiryTime);
    }
}