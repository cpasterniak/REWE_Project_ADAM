package rueckruf.orm_rewe;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Endpoint für Client-Verbindungen
                .setAllowedOriginPatterns("*") // CORS (für Entwicklung)
                .withSockJS(); // Fallback für Browser ohne WebSocket
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Präfix für Subscriptions
        registry.setApplicationDestinationPrefixes("/app"); // Präfix für Nachrichten an @MessageMapping
    }
}