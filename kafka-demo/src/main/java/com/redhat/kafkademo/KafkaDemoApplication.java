package com.redhat.kafkademo;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaDemoApplication implements CommandLineRunner {
	
	public static Logger logger = LoggerFactory.getLogger(KafkaDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}
	
    private final CountDownLatch latch = new CountDownLatch(10);

    @Override
    public void run(String... args) throws Exception {}

    @KafkaListener(topics = "my-topic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("RECEIVED ----> " + cr.toString());
        latch.countDown();
    }
}
