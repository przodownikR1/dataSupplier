package pl.java.scalatech.dataSupplier;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.java.scalatech.dataSupplier.domain.Card;
import pl.java.scalatech.dataSupplier.event.CreatedEvent;
import pl.java.scalatech.dataSupplier.generator.CardGenerator;
import pl.java.scalatech.dataSupplier.messaging.DomainEventsPublisher;

@SpringBootApplication
public class DataSupplierApplication {

    @Autowired
    DomainEventsPublisher eventPublisher;
    
  public static void main(String[] args) {
    SpringApplication.run(DataSupplierApplication.class, args);
  }

  @Bean
  Random random() {
    return new Random();
  }

  @Bean
  CommandLineRunner init() {

    return new CommandLineRunner() {
      final ExecutorService executor = Executors.newFixedThreadPool(2);

      @Override
      public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            Future<Card> submit = executor.submit(new CardGenerator(random()));
            eventPublisher.publish(new CreatedEvent(UUID.randomUUID(), new CreatedEvent.Payload(submit.get())));
        }
        executor.shutdown();
      }
    };
  }
}
