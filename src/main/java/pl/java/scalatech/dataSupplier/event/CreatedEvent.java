package pl.java.scalatech.dataSupplier.event;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import pl.java.scalatech.dataSupplier.domain.Card;

public final class CreatedEvent implements Event {

    private static final long serialVersionUID = 1L;
    @NotNull
    private final UUID uuid;

    @Override
    public UUID uuid() {
        return UUID.randomUUID();
    }

    @NonNull
    private final Payload payload;

    @JsonCreator
    public CreatedEvent(@JsonProperty("uuid") UUID uuid,
            @JsonProperty("payload") Payload payload) {
        this.uuid = uuid();
        this.payload = payload;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    public static class Payload implements Serializable {

        private static final long serialVersionUID = 5287312120524301221L;

        @JsonCreator
        public Payload(@JsonProperty("Card") Card card) {
            this.card = card;
        }

        @NotBlank
        private final Card card;

    }
}
