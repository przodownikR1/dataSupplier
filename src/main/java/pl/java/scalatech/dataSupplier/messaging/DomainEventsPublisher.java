package pl.java.scalatech.dataSupplier.messaging;

import pl.java.scalatech.dataSupplier.event.Event;
@FunctionalInterface
public interface DomainEventsPublisher {
    void publish(Event event);
}
