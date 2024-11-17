package com.kafka.consumer.streaming.consumer;

import com.kafka.consumer.dto.BankTransactionPayload;
import com.kafka.consumer.service.BankService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaConsumer {

  private final BankService bankService;

  @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 1000, multiplier = 1.0))
  @KafkaListener(topics = {"#{'${spring.kafka.topic.name}'}"})
  public void listener(@Valid BankTransactionPayload bankTransaction) {
    log.info("Message received: {}", bankTransaction);
    try {
      bankService.sendMoney(bankTransaction);
    } catch (Exception e) {
      log.error("Error processing message: {}", e.getMessage());
      throw e;
    }
  }

  @DltHandler
  public void dlt(BankTransactionPayload bankTransaction) {
    log.error("Message sent to DLT: {}", bankTransaction);
  }
}
