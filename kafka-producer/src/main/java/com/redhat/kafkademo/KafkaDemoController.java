package com.redhat.kafkademo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KafkaDemoController {

    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private KafkaTemplate<String, String> messageTemplate;

    @GetMapping("/")
    @ResponseBody
    public Message sayHello() {
        KafkaDemoApplication.logger.info("Sent: HELLO-WORLD" );
    	this.messageTemplate.send("my-topic", "HELLO-WORLD");
        return new Message(counter.incrementAndGet(), "Hello World!");
    }
    
    @GetMapping("/msg/{value}")
    @ResponseBody
    public Message sendMessage(@PathVariable String value) {
    	KafkaDemoApplication.logger.info("Sent: " + value);
    	this.messageTemplate.send("my-topic", value);
    	return new Message(counter.incrementAndGet(), "Sent: " + value);
    }
}