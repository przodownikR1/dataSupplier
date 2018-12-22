package pl.java.scalatech.dataSupplier.event;

import java.util.UUID;

public final class ModifiedEvent implements Event{

    private static final long serialVersionUID = 7534178522018645360L;

    @Override
    public UUID uuid() {
        return UUID.randomUUID();
    }

}
