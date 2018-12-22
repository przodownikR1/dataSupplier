package pl.java.scalatech.dataSupplier.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import lombok.Value;

@Value
public final class Person implements Serializable {

    private static final long serialVersionUID = -7295202977921565282L;
    private final String name, email, city, country;
    private final BigDecimal salary;
    private final Position position;
    private final Set<Card> cards;

}