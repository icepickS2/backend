package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub"); // sub로부터 수신
        config.setApplicationDestinationPrefixes("/pub"); // pub로 발송
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // client는 new SockJS('ws')로 이 위치와 연결된다.
        registry.addEndpoint("/ws") // ws://localhost:8080/ws 을 열어둔다.
            .setAllowedOrigins("*") //cors
            .withSockJS();
    }

}