package com.kafka.consumer.streaming.consumer;

import com.kafka.consumer.dto.MessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaConsumer {

  @KafkaListener(topics = {"#{'${spring.kafka.topic.name}'}"})
  public void listener(MessageDTO message) {
    log.info("Message received: {}", message.message());
  }
}
