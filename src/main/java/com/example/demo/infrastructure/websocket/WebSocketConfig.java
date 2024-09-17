package com.example.demo.infrastructure.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration// Le decimos a spring boot que esta clase se encargará de configurar
@EnableWebSocketMessageBroker//Para usar y activar los web sockets
// Esta clase debe implementar esta interfaz en completo para personalizar los métodos de seguridad
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Especificamos el prefijo para recibir la información de los mensajes
        config.enableSimpleBroker("/topic");
        // Especificamos el prefijo para enviar la información de los mensajes
        config.setApplicationDestinationPrefixes("/app");
    }

    // COn este método podemos registrar nuestro endpoint
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-endpoint")
                .setAllowedOrigins("*");
    }
}
