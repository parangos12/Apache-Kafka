package com.kafka.producer.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

  @Value("${spring.kafka.topic.name}")
  private String topicName;

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message) {
    kafkaTemplate.send(topicName, message);
  }
}
