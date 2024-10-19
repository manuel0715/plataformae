package com.plataformae.ws.domain;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private String generatedOtp;
    private LocalDateTime expiryTime;

    private final Random rand = SecureRandom.getInstanceStrong();

    public OtpService() throws NoSuchAlgorithmException {
    }

    // Generar OTP
    public String generateOtp(int otpExpiration) {
        int rValue = this.rand.nextInt(900000);
        int otp = 100000 + rValue; // OTP de 6 dígitos
        generatedOtp = String.valueOf(otp);
        expiryTime = LocalDateTime.now().plusMinutes(otpExpiration);
        return generatedOtp;
    }

    public boolean validateOtp(String inputOtp) {
        return inputOtp.equals(generatedOtp) && LocalDateTime.now().isBefore(expiryTime);
    }
}