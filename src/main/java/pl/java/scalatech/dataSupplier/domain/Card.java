package pl.java.scalatech.dataSupplier.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
public final class Card implements Serializable {
    private static final long serialVersionUID = -757379093260820364L;

    private final String cardHolderName;

    private final String cardNumber;

    private final String cardType;

    private final String expiry;

    private final Currency currency;

   

}