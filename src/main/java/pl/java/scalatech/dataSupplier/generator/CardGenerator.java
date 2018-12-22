package pl.java.scalatech.dataSupplier.generator;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import io.micrometer.core.annotation.Timed;
import pl.java.scalatech.dataSupplier.domain.Card;
import pl.java.scalatech.dataSupplier.domain.Currency;

@Component
public class CardGenerator {

    private final Random random;
    private final Faker faker;
    Currency[] currencies;

    public CardGenerator(Random random) {
        this.random = random;
        faker = new Faker();
        currencies = Currency.values();
    }

    @Timed("cardGenerator")
    public Card generateSingleCard() {
        return new Card(faker.company().industry() + "_" + faker.numerify("CARD???MY"),
                faker.business().creditCardNumber(),
                faker.business().creditCardType(),
                faker.business().creditCardExpiry(),
                currencies[random.nextInt(currencies.length - 1)]);

    }

}
