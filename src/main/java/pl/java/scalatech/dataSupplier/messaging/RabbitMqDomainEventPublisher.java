package pl.java.scalatech.dataSupplier.messaging;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;

import lombok.Value;
import pl.java.scalatech.dataSupplier.event.Event;

@Value
class RabbitMqDomainEventPublisher implements DomainEventsPublisher {

    private final Source source;

    @Override
    public void publish(Event event) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", event.type());
        source.output().send(new GenericMessage<>(event, headers));
    }
}
