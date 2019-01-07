package pl.java.scalatech.dataSupplier;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@EnableAsync
@Configuration
@Slf4j
public class SpringAsynchConfig implements AsyncConfigurer {

    /*
     * @Override
     * public Executor getAsyncExecutor() {
     * ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
     * executor.setCorePoolSize(5);
     * executor.setMaxPoolSize(9);
     * executor.setQueueCapacity(50);
     * executor.setThreadNamePrefix("Ch08Executor-");
     * executor.setWaitForTasksToCompleteOnShutdown(true);
     * executor.setKeepAliveSeconds(5000);
     * executor.setAwaitTerminationSeconds(1000);
     * executor.initialize();
     * return executor;
     * }
     */
    @Bean("executor")
    @Override
    public Executor getAsyncExecutor() {
        ConcurrentTaskExecutor executor = new ConcurrentTaskExecutor(Executors.newFixedThreadPool(100));
        executor.setTaskDecorator(new TaskDecorator() {
            @Override
            public Runnable decorate(Runnable runnable) {
                return () -> {

                    long t = System.currentTimeMillis();
                    runnable.run();
                    log.info("creating ConcurrentTaskExecutor thread pool....");
                    log.info("Thread %s has a processing time:  {},{}", Thread.currentThread().getName(),(System.currentTimeMillis() - t));
                };
            }
        });
        return executor;
    }
}