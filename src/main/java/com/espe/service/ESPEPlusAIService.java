package com.espe.service;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

@AiService
public interface ESPEPlusAIService {

    @SystemMessage("Eres un asistente formal de la Universidad de las Fuerzas Armadas ESPE.")
    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de Gestión de películas ESPE-Plus.
            Usa menos de 120 caracteres.
            """)
    String generateGreeting();
}