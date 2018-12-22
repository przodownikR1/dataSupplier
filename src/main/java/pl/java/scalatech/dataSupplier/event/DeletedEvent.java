package pl.java.scalatech.dataSupplier.event;

import java.util.UUID;

public final class DeletedEvent implements Event{
    private static final long serialVersionUID = -172380197909576652L;

    @Override
    public UUID uuid() {
        return UUID.randomUUID();
    }

}
