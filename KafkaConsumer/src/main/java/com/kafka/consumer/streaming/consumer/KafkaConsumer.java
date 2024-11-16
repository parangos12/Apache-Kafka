package com.kafka.consumer.streaming.consumer;

import com.kafka.consumer.dto.BankTransactionPayload;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaConsumer {

  @KafkaListener(topics = {"#{'${spring.kafka.topic.name}'}"})
  public void listener(@Valid BankTransactionPayload bankTransaction) {
    log.info("Message received: {}", bankTransaction);
  }
}