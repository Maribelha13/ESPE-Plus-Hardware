package com.espe.web.controller;

import com.espe.service.HardwareService;
import com.espe.service.ESPEPlusAIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hardware")
public class HardwareController {

    private final HardwareService hardwareService;
    private final ESPEPlusAIService aiService;

    @Value("${spring.application.name:ESPE_Plus}")
    private String appName;


    // Inyección por constructor de ambos servicios necesarios
    public HardwareController(HardwareService hardwareService, ESPEPlusAIService aiService) {
        this.hardwareService = hardwareService;
        this.aiService = aiService;
    }

    /**
     * Endpoint para evaluar el rendimiento del Enfoque Imperativo
     */
    @GetMapping("/reporte/imperativo")
    public Map<String, Object> obtenerReporteImperativo() {
        long inicio = System.currentTimeMillis();

        var resultado = hardwareService.procesarImperativo();

        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("sistema", appName);
        respuesta.put("paradigma", "Imperativo (Bucles Clásicos)");
        respuesta.put("tiempoEjecucionMs", tiempoTotal);
        respuesta.put("reporte", resultado);

        return respuesta;
    }

    /**
     * Endpoint para evaluar el rendimiento del Enfoque Funcional / Declarativo
     */
    @GetMapping("/reporte/funcional")
    public Map<String, Object> obtenerReporteFuncional() {
        long inicio = System.currentTimeMillis();

        var resultado = hardwareService.procesarFuncional();

        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("sistema", appName);
        respuesta.put("paradigma", "Declarativo / Funcional (Java Streams API)");
        respuesta.put("tiempoEjecucionMs", tiempoTotal);
        respuesta.put("reporte", resultado);

        return respuesta;
    }
}