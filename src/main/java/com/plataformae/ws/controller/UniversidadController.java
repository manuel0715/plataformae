package com.plataformae.ws.controller;


import com.plataformae.ws.service.IUniversidadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/universidad")
public class UniversidadController {


    private final IUniversidadService iUniversidadService;

    public UniversidadController(IUniversidadService iUniversidadService) {
        this.iUniversidadService = iUniversidadService;
    }


}
