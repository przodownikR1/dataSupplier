package pl.java.scalatech.dataSupplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.dataSupplier.domain.Card;
import pl.java.scalatech.dataSupplier.event.CreatedEvent;
import pl.java.scalatech.dataSupplier.generator.CardGenerator;
import pl.java.scalatech.dataSupplier.messaging.DomainEventsPublisher;

@Slf4j
@SpringBootApplication
public class DataSupplierApplication {

  @Autowired DomainEventsPublisher eventPublisher;

  public static void main(String[] args) {
    SpringApplication.run(DataSupplierApplication.class, args);
  }

  @Bean
  Random random() {
    return new Random();
  }

  @Bean
  @Timed("generatedCard")
  CommandLineRunner init() {
    return new CommandLineRunner() {
      final ExecutorService executor = Executors.newFixedThreadPool(20);

      @Override
      public void run(String... args) throws Exception {
        log.info("+++ Start generate samples");
        for (int j = 0; j < 10; j++) {
          Collection<Callable<Card>> list = prepareTask();
          log.info("+++ Generated  for interation [{}] : {}", j, list.size());
          executor
              .invokeAll(list)
              .stream()
              .forEach(
                  future -> {
                    try {
                      eventPublisher.publish(
                          new CreatedEvent(
                              UUID.randomUUID(), new CreatedEvent.Payload(future.get())));
                    } catch (Exception e) {
                      e.printStackTrace();
                    }
                  });
        }
        executor.shutdown();
      }

      @Timed("prepareGenerateSamples")
      private Collection<Callable<Card>> prepareTask() throws InterruptedException {
        Collection<Callable<Card>> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
          Thread.sleep(1000);
          list.add(new CardGenerator(random()));
        }
        return list;
      }
    };
  }
}
