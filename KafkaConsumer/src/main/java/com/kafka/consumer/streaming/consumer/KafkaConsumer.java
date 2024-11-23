package com.kafka.consumer.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.dto.BankTransactionPayload;
import com.kafka.consumer.entity.BankTransaction;
import com.kafka.consumer.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer {

  private final BankService bankService;

  @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 3000, multiplier = 1))
  @KafkaListener(topics = {"#{'${spring.kafka.topic.name}'}"})
  public void listener(
      @Payload BankTransactionPayload bankTransaction,
      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
      @Header(KafkaHeaders.OFFSET) long offset) throws JsonProcessingException {
    try {
      log.info(
          "Received: {} from {} offset {}",
          new ObjectMapper().writeValueAsString(bankTransaction),
          topic,
          offset);
      bankService.sendMoney(bankTransaction);
      BankTransaction bankTransactionNew =
          new BankTransaction(
              bankTransaction.getOriginAccountId(),
              bankTransaction.getDestinationAccountId(),
              bankTransaction.getTransactionType(),
              bankTransaction.getMoney());
      bankService.saveTransaction(bankTransactionNew);
    }
    catch (Exception ex) {
      log.error("Error processing message: {}", ex.getMessage());
      throw ex;
    }
  }

  @DltHandler
  public void dlt(
      BankTransactionPayload bankTransaction,
      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
      @Header(KafkaHeaders.OFFSET) long offset) {
    log.error(
        "Message sent to DLT: {} from topic {} with offset {}", bankTransaction, topic, offset);
  }
}
