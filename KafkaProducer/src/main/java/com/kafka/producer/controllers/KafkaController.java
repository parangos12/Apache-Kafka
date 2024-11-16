package com.kafka.producer.controllers;

import com.kafka.producer.stream.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka/producer")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to the Kafka Topic successfully");
    }
}
