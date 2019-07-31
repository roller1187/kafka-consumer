package com.redhat.kafkaconsumer;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaDemoApplication implements CommandLineRunner {
	
	public static Logger logger = LoggerFactory.getLogger(KafkaDemoApplication.class);
	
	JSONObject JSONAcrostic = new JSONObject();
	static KafkaDemoController kafkaController = new KafkaDemoController();
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}
	
    private final CountDownLatch latch = new CountDownLatch(10);

    @Override
    public void run(String... args) throws Exception {}

    @KafkaListener(topics = "my-topic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("MESSAGE RECEIVED: \n" + 
        				"	TOPIC: " + cr.topic() + "\n" +
        				"	PARTITION: " + cr.partition() + "\n" +
        				"	OFFSET: " + cr.offset() + "\n" +
        				"	VALUE: " + cr.value().toString());
        
        JSONAcrostic = kafkaController.createAcrostic(cr.value().toString());
        logger.info("Message forwarded to ui topic: " + kafkaController.sendMessage(JSONAcrostic));
        latch.countDown();
    }
}
