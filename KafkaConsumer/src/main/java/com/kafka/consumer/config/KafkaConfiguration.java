package com.kafka.consumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration implements KafkaListenerConfigurer {

  private final LocalValidatorFactoryBean validatorFactory;

  @Override
  public void configureKafkaListeners(KafkaListenerEndpointRegistrar register) {
    register.setMessageHandlerMethodFactory(kafkaHandlerMethodFactory());
  }

  @Bean
  public MessageHandlerMethodFactory kafkaHandlerMethodFactory() {
    DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
    factory.setValidator(validatorFactory);
    return factory;
  }
}
