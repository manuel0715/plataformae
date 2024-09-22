package com.plataformae.ws.controller;

import com.plataformae.ws.domain.EmailService;
import com.plataformae.ws.domain.OtpService;
import com.plataformae.ws.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.plataformae.ws.util.Utils.buildResponse;
import static com.plataformae.ws.util.Utils.enmascararEmail;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/generar")
    public ResponseEntity<ApiResponse<String>> generateOtp(@RequestParam String email) {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
             if (!email.matches(regex)){
                 return buildResponse(
                         "Email Invalido",
                         null,
                         HttpStatus.BAD_REQUEST
                 );
             }
        String otp = otpService.generateOtp();


        emailService.sendOtpEmail(email, otp);

        return buildResponse(
                "Para completar la verificación de tu correo, " +
                        "hemos enviado un código de verificación (OTP) a tu correo electrónico: " +
                        enmascararEmail(email) + ". Recuerda que este código es válido por 5 minutos.",
                    null,
                        HttpStatus.OK
        );
    }

    @PostMapping("/validar")
    public ResponseEntity<ApiResponse<String>> validateOtp(@RequestParam String otp) {
        boolean isValid = otpService.validateOtp(otp);
        if (isValid) {
            return buildResponse(
                    "Validación exitosa",
                    null,
                    HttpStatus.OK
            );
        } else {

            return buildResponse(
                    "El código OTP es inválido o ha expirado. Por favor, intenta generar uno nuevo.",
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
