package pl.java.scalatech.dataSupplier.messaging;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

@EnableBinding(Source.class)
class MessagingConfig {

    @Bean
    DomainEventsPublisher eventPublisher(Source source) {
        return new RabbitMqDomainEventPublisher(source);
    }

}
