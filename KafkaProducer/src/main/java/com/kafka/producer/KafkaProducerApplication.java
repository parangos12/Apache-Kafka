package com.kafka.producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			kafkaTemplate.send("topic-sample", "Rome Deny Peter 3 Backward Quintus");
		};
	}

}
