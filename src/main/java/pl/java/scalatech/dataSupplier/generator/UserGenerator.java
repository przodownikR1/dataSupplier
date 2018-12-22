package pl.java.scalatech.dataSupplier.generator;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;

import com.github.javafaker.Faker;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.dataSupplier.domain.Card;
import pl.java.scalatech.dataSupplier.domain.Person;
import pl.java.scalatech.dataSupplier.domain.Position;

@Slf4j
public class UserGenerator {

    private final Random random;
    private final Faker faker;
    Position[] positions;

    public UserGenerator(Random random) {
        this.random = random;
        faker = new Faker();
        positions = Position.values();

    }

    @Timed("userGenerator")
    public Person generateSingleUser(Set<Card> cards) {
        Person person = new Person(faker.name().fullName(),
                faker.internet().emailAddress(), faker.address().cityName(),
                faker.address().country(), new BigDecimal(random.nextInt(10000)),
                positions[random.nextInt(positions.length - 1)], cards);
        log.debug("user: {}", person);
        return person;
    }

}