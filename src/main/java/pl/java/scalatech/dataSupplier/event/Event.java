package pl.java.scalatech.dataSupplier.event;

import static pl.java.scalatech.dataSupplier.MessagingConstants.EVENT_DOMAIN_VERSION;
import static pl.java.scalatech.dataSupplier.MessagingConstants.EVENT_NAMESPACE;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import pl.java.scalatech.dataSupplier.MessagingConstants;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = MessagingConstants.EVENT_CREATED_DOMAIN, value = CreatedEvent.class),
        @JsonSubTypes.Type(name = MessagingConstants.EVENT_MODIFIED_DOMAIN, value = ModifiedEvent.class),
        @JsonSubTypes.Type(name = MessagingConstants.EVENT_DELETED_DOMAIN, value = DeletedEvent.class)
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "type", "namespace", "occuredOn", "uuid", "version" })
public interface Event extends Serializable {
    
    UUID uuid();

    default OffsetDateTime occuredOn() {
        return OffsetDateTime.now();
    }

    default String namespace() {
        return EVENT_NAMESPACE;
    }

    default String version() {
        return EVENT_DOMAIN_VERSION;
    }

    default String type() {
        return this.getClass().getSimpleName();
    }
}