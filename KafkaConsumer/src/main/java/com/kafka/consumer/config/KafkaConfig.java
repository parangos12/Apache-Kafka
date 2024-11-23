package com.kafka.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;

@Configuration
public class KafkaConfig {
  @Bean
  public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
      ConsumerFactory<Object, Object> kafkaConsumerFactory) {
    ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(kafkaConsumerFactory);
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

    // Configuración del ErrorHandler con un número muy alto de reintentos
    int maxRetries = Integer.MAX_VALUE; // Usando el máximo valor entero como "ilimitado"
    ExponentialBackOffWithMaxRetries backOff = new ExponentialBackOffWithMaxRetries(maxRetries);
    backOff.setInitialInterval(1000L); // tiempo inicial entre reintentos
    backOff.setMaxInterval(10000L); // tiempo máximo entre reintentos
    backOff.setMultiplier(2.0); // multiplicador para incrementar el tiempo entre reintentos
    DefaultErrorHandler errorHandler = new DefaultErrorHandler(backOff);

    factory.setCommonErrorHandler(errorHandler);
    return factory;
  }
}
