package com.kafka.consumer.handler;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaErrorHandler implements CommonErrorHandler {

  @Override
  public boolean handleOne(
      @NonNull Exception exception,
      @NonNull ConsumerRecord<?, ?> consumerRecord,
      @NonNull Consumer<?, ?> consumer,
      @NonNull MessageListenerContainer container) {
    log.error(
        "Failed during consuming message from kafka topic {} with offset {}. Error: {}. Root cause: {}",
        Optional.ofNullable(consumerRecord.topic()).orElse(StringUtils.EMPTY),
        consumerRecord.offset(),
        exception.getMessage(),
        ExceptionUtils.getRootCauseMessage(exception));
    return true;
  }

}
