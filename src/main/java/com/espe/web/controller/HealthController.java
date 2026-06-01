package com.espe.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {


    @Value("${environment.name:Desconocido}")
    private String environmentName;

    @GetMapping("/health")
    public Map<String, String> getHealthStatus() {
        return Map.of(
                "status", "UP",
                "environment", environmentName,
                "message", "La aplicación de gestión de películas ESPE-Plus funciona correctamente."
        );
    }
}