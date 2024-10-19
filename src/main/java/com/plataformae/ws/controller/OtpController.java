package com.plataformae.ws.controller;

import com.plataformae.ws.domain.EmailService;
import com.plataformae.ws.domain.OtpService;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.OtpResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.plataformae.ws.util.Utils.buildResponse;
import static com.plataformae.ws.util.Utils.maskEmail;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Value("${otp.expiration}")
    private int otpExpiration;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/generar")
    public ResponseEntity<ApiResponseDTO<OtpResponseDTO>> generateOtp(@RequestParam String email) throws MessagingException {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
             if (!email.matches(regex)){
                 return buildResponse(
                         "Email Invalido",
                         null,
                         HttpStatus.BAD_REQUEST
                 );
             }
        String otp = otpService.generateOtp(otpExpiration);
             String subject ="Tu OTP de verificación "+otp;
             String body ="Tu código OTP es: " + otp + ",por favor ingresa este codigo para completar tu registro.<br> Recuerda este código expira en 5 minutos.";

        emailService.sendEmail(email, subject,body);

        OtpResponseDTO otpResponseDTO = new OtpResponseDTO();

        String mensaje="Para completar tu registro, " +
                "hemos enviado un código de verificación (OTP) a tu correo electrónico: " +
                maskEmail(email) + ". Recuerda que este código es válido por "+otpExpiration+" minutos.";

        otpResponseDTO.setEmail(maskEmail(email));
        otpResponseDTO.setMensaje(mensaje);
        otpResponseDTO.setTiempoExpiracion(otpExpiration);

        return buildResponse("Otp enviado",
                otpResponseDTO,
                        HttpStatus.OK
        );
    }

    @PostMapping("/validar")
    public ResponseEntity<ApiResponseDTO<String>> validateOtp(@RequestParam String otp) {
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
